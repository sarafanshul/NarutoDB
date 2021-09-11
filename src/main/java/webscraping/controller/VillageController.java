package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webscraping.model.village.VillageDoc;
import webscraping.service.VillageService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(value = "village")
public class VillageController {
    @Autowired
    VillageService villageService;

    @PostMapping(value = "id/{id}")
    public ResponseEntity<Void> saveVillage(@PathVariable String id) {
        log.info("Starting get info for village: {}", id);
        villageService.insert(id);
        log.info("Village {} info saved.", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity<VillageDoc> getVillage(@PathVariable String id) {
        VillageDoc village = villageService.getVillage(id);
        return ResponseEntity.ok().body(village);
    }

    @GetMapping(value = "like/{name}")
    public ResponseEntity<Collection<VillageDoc>> getCharacterLike(@PathVariable String name) {
        return ResponseEntity.ok().body(villageService.getVillageByNameEnglishRegex(name));
    }

    @GetMapping(value = "all")
    public ResponseEntity<Collection<VillageDoc>> getAllCharacter() {
        return ResponseEntity.ok().body(villageService.getAllVillages());
    }

    /**
     * For getting all villages with paging
     * <br>
     * Usage
     * <br>
     * <i>village/<b>page/?page=1&size=5&sort=name.english<b/></i>
     *
     * @param pageable Pageable params
     * @return Paged response entity
     */
    @GetMapping(value = "page")
    public ResponseEntity<Page<VillageDoc>> getAllVillagePaged(
        @PageableDefault(
            size = 20
        ) Pageable pageable
    ) {
        return ResponseEntity.ok().body(villageService.getAllVillagesPaged(pageable));
    }

}
