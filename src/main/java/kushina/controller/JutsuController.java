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
import kushina.model.jutsu.JutsuDoc;
import kushina.service.JutsuService;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "jutsu")
public class JutsuController {
    @Autowired
    JutsuService jutsuService;

    @PostMapping(value = "id")
    public ResponseEntity<Void> saveJutsu(@RequestParam String id) {
        log.info("Starting get info for jutsu: {}", id);
        jutsuService.insert(id);
        log.info("Jutsu {} info saved.", id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Usage
     * <br>
     * <i>jutsu/<b>id?id=Bo_Shadow_Clone_Technique</b></i>
     * <br>
     * @param id jutsu Id
     * @return 200 with a Jutsu object , 404 otherwise
     */
    @GetMapping(value = "id")
    public ResponseEntity<JutsuDoc> getJutsu(@RequestParam String id) {
        JutsuDoc jutsu = jutsuService.getJutsu(id);
        return ResponseEntity.ok().body(jutsu);
    }

    /**
     * Usage
     * <br>
     * <i>jutsu/<b>like?name=shadow</b></i>
     * <br>
     */
    @GetMapping(value = "like")
    public ResponseEntity<Collection<JutsuDoc>> getJutsuByName(@RequestParam String name) {
        List<JutsuDoc> characters = jutsuService.getJutsuByNameEnglishRegex(name);
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping(value = "all")
    public ResponseEntity<Collection<JutsuDoc>> getAllJutsus() {
        return ResponseEntity.ok().body(jutsuService.getAllJutsus());
    }

    @GetMapping(value = "page")
    public ResponseEntity<Page<JutsuDoc>> getAllJutsusPaged(
        @PageableDefault(size = 20)
            Pageable pageable) {
        return ResponseEntity.ok().body(jutsuService.getAllJutsusPaged(pageable));
    }

    /**
     * Finds the jutsus with ID in name using Regex Match , text is  not indexed ,
     * hence can be slow but offers a partial text search due to wildcard matching
     * <br>
     * Usage
     * <br>
     * <i>jutsus/like_paged/<b>shad?size=10&sort=name.english</b></i> : returns all characters with name.english containing <b>shad</b>
     *
     * @param name to match
     * @return Page of characters found
     */
    @GetMapping(value = "like_paged")
    public ResponseEntity<Page<JutsuDoc>> getJutsuByName(
        @RequestParam String name,
        @PageableDefault(
            size = 20
        ) Pageable pageable
    ) {
        Page<JutsuDoc> characters = jutsuService.getJutsuByNameEnglishRegexPaged(name, pageable);
        return ResponseEntity.ok().body(characters);
    }

}
