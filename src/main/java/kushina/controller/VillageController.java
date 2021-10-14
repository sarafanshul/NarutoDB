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
import kushina.model.village.VillageDoc;
import kushina.service.VillageService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(value = "village")
public class VillageController {
    @Autowired
    VillageService villageService;

    @PostMapping(value = "id")
    public ResponseEntity<Void> saveVillage(@RequestParam String id) {
        log.info("Starting get info for village: {}", id);
        villageService.insert(id);
        log.info("Village {} info saved.", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "id")
    public ResponseEntity<VillageDoc> getVillage(@RequestParam String id) {
        VillageDoc village = villageService.getVillage(id);
        return ResponseEntity.ok().body(village);
    }

    @GetMapping(value = "like")
    public ResponseEntity<Collection<VillageDoc>> getCharacterLike(@RequestParam String name) {
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
