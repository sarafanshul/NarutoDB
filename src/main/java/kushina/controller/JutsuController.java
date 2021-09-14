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

    @PostMapping(value = "id/{id}")
    public ResponseEntity<Void> saveJutsu(@PathVariable String id) {
        log.info("Starting get info for jutsu: {}", id);
        jutsuService.insert(id);
        log.info("Jutsu {} info saved.", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity<JutsuDoc> getJutsu(@PathVariable String id) {
        JutsuDoc jutsu = jutsuService.getJutsu(id);
        return ResponseEntity.ok().body(jutsu);
    }

    @GetMapping(value = "like/{name}")
    public ResponseEntity<Collection<JutsuDoc>> getJutsuByName(@PathVariable String name) {
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
    @GetMapping(value = "like_paged/{name}")
    public ResponseEntity<Page<JutsuDoc>> getJutsuByName(
        @PathVariable String name,
        @PageableDefault(
            size = 20
        ) Pageable pageable
    ) {
        Page<JutsuDoc> characters = jutsuService.getJutsuByNameEnglishRegexPaged(name, pageable);
        return ResponseEntity.ok().body(characters);
    }

    /*
    Impl
    by usage ,
    by power ,
    by users ,
     */

}
