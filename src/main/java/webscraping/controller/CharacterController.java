package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webscraping.document.CharacterDoc;
import webscraping.service.CharacterService;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "character")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @PostMapping(value = "id/{id}")
    public ResponseEntity<String> saveCharacter(@PathVariable String id) {
        log.info("Starting get infos for character: {}", id);
        characterService.insert(id); // return object if needed .?
        log.info("Character {} infos saved.", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity<CharacterDoc> getCharacter(@PathVariable String id) {
        log.info("Searching infos for character: {}", id);
        CharacterDoc character = characterService.getCharacter(id);
        log.info("Infos getted for character: {}", id);
        return ResponseEntity.ok().body(character);
    }

    /**
     * Finds the characters with ID in name using match and $text , $search
     *
     * @param name to match
     * @return List of characters found
     */
    @GetMapping(value = "name/{name}")
    public ResponseEntity<Collection<CharacterDoc>> getCharactersByName( @PathVariable String name ){
        log.info("Searching infos for character: {}", name);
        List<CharacterDoc> characters = characterService.getCharactersByName(name);
        log.info("Infos getted for character: {}", name);
        return ResponseEntity.ok().body(characters);
    }

}