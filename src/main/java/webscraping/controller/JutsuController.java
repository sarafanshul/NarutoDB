package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webscraping.model.jutsu.JutsuDoc;
import webscraping.service.JutsuService;

@Slf4j
@RestController
@RequestMapping(value = "jutsu")
public class JutsuController {
    @Autowired
    JutsuService jutsuService;

    @PostMapping(value = "id/{id}")
    public ResponseEntity<Void> saveJutsu(@PathVariable String id) {
        log.warn("Jutsu Info -> getDescription does not work correctly");
        log.info("Starting get info for jutsu: {}", id);
        jutsuService.insert(id);
        log.info("Jutsu {} info saved.", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity<JutsuDoc> getJutsu(@PathVariable String id) {
        log.info("Searching infos for jutsu: {}", id);
        JutsuDoc jutsu = jutsuService.getJutsu(id);
        log.info("Infos getted for jutsu: {}", id);
        return ResponseEntity.ok().body(jutsu);
    }
}
