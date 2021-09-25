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

    // new endpoints for sort out episodes by season ie Naruto , Naruto S


}
