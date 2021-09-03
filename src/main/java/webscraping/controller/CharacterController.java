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

    /**
     * Creates a characters with ID , if invalid throws [ResponseStatusException]
     *
     * @param id to match
     * @return List of characters found
     */
    @PostMapping(value = "id/{id}")
    public ResponseEntity<String> saveCharacter(@PathVariable String id) {
        log.info("Starting get info for character: {}", id);
        characterService.insert(id); // return object if needed .?
        log.info("Character {} info saved.", id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Finds the characters with ID
     *
     * @param id to match
     * @return List of characters found
     */
    @GetMapping(value = "id/{id}")
    public ResponseEntity<CharacterDoc> getCharacter(@PathVariable String id) {
        log.info("Searching info for character: {}", id);
        CharacterDoc character = characterService.getCharacter(id);
        log.info("Info retrieved for character(REGEX): {}", id);
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
        log.info("Searching info for character: {}", name);
        List<CharacterDoc> characters = characterService.getCharactersByName(name);
        log.info("Info retrieved for character(REGEX): {}", name);
        return ResponseEntity.ok().body(characters);
    }

    /**
     * Finds the characters with ID in name using Regex Match , not indexed like above ,
     * hence can be slow but offers a partial text search due to wildcard matching
     *
     * @param name to match
     * @return List of characters found
     */
    @GetMapping(value = "like/{name}")
    public ResponseEntity<Collection<CharacterDoc>> getCharacterLike( @PathVariable String name ){
        log.info("Searching info for character(REGEX): {}", name);
        List<CharacterDoc> characters = characterService.getCharacterByNameEnglishRegex(name);
        log.info("Info retrieved for character(REGEX): {}", name);
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping(value = "all")
    public ResponseEntity<Collection<CharacterDoc>> getAllCharacter(){
        return ResponseEntity.ok().body( characterService.getAllCharacters() );
    }

}