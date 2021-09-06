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

    @PostMapping(value = "/{name}")
    public ResponseEntity<Void> saveJutsu(@PathVariable String name) {
        log.warn("Jutsu Info -> getDescription does not work correctly");
        log.info("Starting get infos for jutsu: {}", name);
        jutsuService.insert(name);
        log.info("Jutsu {} infos saved.", name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<JutsuDoc> getJutsu(@PathVariable String name) {
        log.info("Searching infos for jutsu: {}", name);
        JutsuDoc jutsu = jutsuService.getJutsu(name);
        log.info("Infos getted for jutsu: {}", name);
        return ResponseEntity.ok().body(jutsu);
    }
}
