package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webscraping.document.VillageDoc;
import webscraping.dto.VillageDTO;
import webscraping.service.VillageService;

@Slf4j
@RestController
@RequestMapping(value = "village")
public class VillageController {
    @Autowired
    VillageService villageService;

    @PostMapping(value = "/{name}")
    public ResponseEntity<Void> saveVillage(@PathVariable String name) {
        log.info("Starting get infos for village: {}", name);
        VillageDoc villageDoc = villageService.getVillageInfo(name);
        villageService.insert(villageDoc);
        log.info("Village {} infos saved.", name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<VillageDTO> getVillage(@PathVariable String name) {
        log.info("Searching infos for village: {}", name);
        VillageDTO village = villageService.getVillage(name);
        log.info("Infos getted for village: {}", name);
        return ResponseEntity.ok().body(village);
    }
}
