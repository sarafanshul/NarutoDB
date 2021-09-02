package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webscraping.document.JutsuDoc;
import webscraping.dto.JutsuDTO;
import webscraping.service.JutsuService;

@Slf4j
@RestController
@RequestMapping(value = "jutsu")
public class JutsuController {
    @Autowired
    JutsuService jutsuService;

    @PostMapping(value = "/{name}")
    public ResponseEntity<Void> saveJutsu(@PathVariable String name) {
        log.info("Starting get infos for jutsu: {}", name);
        JutsuDoc jutsuDoc = jutsuService.getJutsuInfo(name);
        jutsuService.insert(jutsuDoc);
        log.info("Jutsu {} infos saved.", name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<JutsuDTO> getJutsu(@PathVariable String name) {
        log.info("Searching infos for jutsu: {}", name);
        JutsuDTO jutsu = jutsuService.getJutsu(name);
        log.info("Infos getted for jutsu: {}", name);
        return ResponseEntity.ok().body(jutsu);
    }
}
