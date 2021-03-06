/*
 * @author Anshul Saraf
 */

package kushina.controller;

import kushina.model.clan.ClanDoc;
import kushina.service.ClanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "clan")
public class ClanController {

    @Autowired
    ClanService clanService;

    @PostMapping(value = "id")
    public ResponseEntity<Void> saveClan(@RequestParam String id) {
        log.info("Starting get info for clan: {}", id);
        clanService.insert(id);
        log.info("Clan {} info saved.", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "id")
    public ResponseEntity<ClanDoc> getClan(@RequestParam String id) {
        ClanDoc clan = clanService.getClan(id);
        return ResponseEntity.ok().body(clan);
    }

    @GetMapping(value = "like")
    public ResponseEntity<Collection<ClanDoc>> getCharactersByName(@RequestParam String name) {
        List<ClanDoc> characters = clanService.getClanByNameEnglishRegex(name);
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

    @GetMapping(value = "order_by_members")
    public ResponseEntity<Page<ClanDoc>> getClansOrderedByMemberSize(
        @PageableDefault(size = 50)
            Pageable pageable) {
        return ResponseEntity.ok().body(clanService.getClansOrderedByMemberSize(pageable));
    }

    @GetMapping(value = "order_by_jutsus")
    public ResponseEntity<Page<ClanDoc>> getClansOrderedByJutsusSize(
        @PageableDefault(size = 50)
            Pageable pageable) {
        return ResponseEntity.ok().body(clanService.getClansOrderedByJutsusSize(pageable));
    }

}
