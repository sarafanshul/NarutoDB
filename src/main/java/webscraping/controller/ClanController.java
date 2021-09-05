package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webscraping.document.ClanDoc;
import webscraping.service.ClanService;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "clan")
public class ClanController {

    @Autowired
    ClanService clanService;

    @PostMapping(value = "id/{id}")
    public ResponseEntity<Void> saveClan(@PathVariable String id) {
        log.info("Starting get info for clan: {}", id);
        clanService.insert(id);
        log.info("Clan {} info saved.", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClanDoc> getClan(@PathVariable String id) {
        log.info("Searching info for clan: {}", id);
        ClanDoc clan = clanService.getClan(id);
        log.info("Info retrieved for clan: {}", id);
        return ResponseEntity.ok().body(clan);
    }

    @GetMapping(value = "name/{name}")
    public ResponseEntity<Collection<ClanDoc>> getCharactersByName(@PathVariable String name) {
        log.info("Searching info for character: {}", name);
        List<ClanDoc> characters = clanService.getClansByName(name);
        log.info("Info retrieved for character(REGEX): {}", name);
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping(value = "like/{name}")
    public ResponseEntity<Collection<ClanDoc>> getCharacterLike(@PathVariable String name) {
        log.info("Searching info for character(REGEX): {}", name);
        List<ClanDoc> characters = clanService.getClanByNameEnglishRegex(name);
        log.info("Info retrieved for character(REGEX): {}", name);
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping(value = "all")
    public ResponseEntity<Collection<ClanDoc>> getAllCharacter() {
        return ResponseEntity.ok().body(clanService.getAllClans());
    }

    @GetMapping(value = "page")
    public ResponseEntity<Page<ClanDoc>> getAllCharactersPaged(
            @PageableDefault(size = 20)
                    Pageable pageable) {
        return ResponseEntity.ok().body(clanService.getAllClansPaged(pageable));
    }

}
