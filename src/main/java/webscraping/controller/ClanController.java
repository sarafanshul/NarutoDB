package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webscraping.document.ClanDoc;
import webscraping.service.ClanService;

@Slf4j
@RestController
@RequestMapping(value = "clan")
public class ClanController {

    @Autowired
    ClanService clanService;

    @PostMapping(value = "/{name}")
    public ResponseEntity<Void> saveClan(@PathVariable String name) {
        log.info("Starting get infos for clan: {}", name);
        clanService.insert(name);
        log.info("Clan {} infos saved.", name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<ClanDoc> getClan(@PathVariable String name) {
        log.info("Searching infos for clan: {}", name);
        ClanDoc clan = clanService.getClan(name);
        log.info("Infos getted for clan: {}", name);
        return ResponseEntity.ok().body(clan);
    }

}
