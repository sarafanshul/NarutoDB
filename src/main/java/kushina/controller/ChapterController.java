/*
 * @author Anshul Saraf
 */

package kushina.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kushina.model.chapter.ChapterDoc;
import kushina.service.ChapterService;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @PostMapping(value = "id")
    public ResponseEntity<Void> insertChapter(@RequestParam String id) {
        chapterService.insert(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "id")
    public ResponseEntity<ChapterDoc> getChapter(@RequestParam String id) {
        ChapterDoc character = chapterService.getChapter(id);
        return ResponseEntity.ok().body(character);
    }

    @GetMapping(value = "all")
    public ResponseEntity<Collection<ChapterDoc>> getAllChapters() {
        return ResponseEntity.ok().body(chapterService.getAllChapters());
    }

    // why would someone want chapters paged and in random order ?
    @Deprecated
    @GetMapping(value = "page")
    public ResponseEntity<Page<ChapterDoc>> getAllChaptersPaged(
        @PageableDefault(size = 20)
            Pageable pageable) {
        return ResponseEntity.ok().body(chapterService.getAllChaptersPaged(pageable));
    }

    @GetMapping(value = "sorted")
    public ResponseEntity<Page<ChapterDoc>> getAllChaptersPagedSorted(
        @PageableDefault(size = 20)
            Pageable pageable) {
        return ResponseEntity.ok().body(chapterService.getAllChaptersPagedSorted(pageable));
    }

    @GetMapping(value = "sorted_all")
    public ResponseEntity<List<ChapterDoc>> getAllChaptersSorted() {
        return ResponseEntity.ok().body(chapterService.getAllChaptersSorted());
    }

    /**
     * For fetching all cannon episodes
     * Usage
     * <br>
     * <i>chapter/<b>cannon?sort=episode.absoluteEpisodeNumber,desc<b/><i/>
     * @param pageable query api
     * @return Page of all cannon episodes
     */
    @GetMapping(value = "cannon")
    public  ResponseEntity<Page<ChapterDoc>> getAllChapterCannon(
        @PageableDefault(size = 20)
            Pageable pageable ){
        return ResponseEntity.ok().body( chapterService.getAllChaptersCannon(pageable) );
    }

    /**
     * <h>Izanami : "she who invites"</h>
     * <br>
     * Aggregation Pipeline to create page:
     * <ol>
     *      <li>with <i>range<i/> w.r.t <b>chapter.episode.absoluteEpisodeNumber<b/></li>
     *      <li>with <i>cannon<i/> episodes</li>
     *      <li>with a custom sort <i>order<i/></li>
     * </ol>
     * @param rangeL starting episode number (inclusive) ,<i>DEFAULTS TO 0</i>
     * @param rangeR end episode number (inclusive) ,<i>DEFAULTS TO MAX</i>
     * @param cannon filter cannon chapters ,<i>DEFAULTS TO FALSE</i>
     * @param sort <b>"1"</b> <i>ASC</i> , <b>"-1"</b> <i>DESC</i> ,<i>DEFAULTS TO ASC</i>
     * @param pageable page param
     * @return Paged data from DB
     */
    @GetMapping(value = "izanami")
    public ResponseEntity<Page<ChapterDoc>> findAllByOrderByEpisodeRangeSorted(
        @RequestParam(required = false) Integer rangeL ,
        @RequestParam(required = false) Integer rangeR ,
        @RequestParam(required = false) Boolean cannon ,
        @RequestParam(required = false) Integer sort ,
        @PageableDefault(size = 20)
            Pageable pageable
    ){
        if( rangeL == null )
            rangeL = 0;
        if( rangeR == null )
            rangeR = 1000 ;
        if( sort == null )
            sort = 1 ; // asc
        if( cannon == null )
            cannon = false ;
        return ResponseEntity.ok(chapterService.findAllByOrderByEpisodeRangeSorted(rangeL, rangeR, cannon, sort, pageable));
    }

}
