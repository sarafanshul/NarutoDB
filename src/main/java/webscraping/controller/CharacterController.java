package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webscraping.model.character.CharacterDoc;
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
        CharacterDoc character = characterService.getCharacter(id);
        return ResponseEntity.ok().body(character);
    }

    /**
     * Finds the characters with ID in name using match and $text , $search
     *
     * @param name to match
     * @return List of characters found
     */
    @GetMapping(value = "name/{name}")
    public ResponseEntity<Collection<CharacterDoc>> getCharactersByName(@PathVariable String name) {
        List<CharacterDoc> characters = characterService.getCharactersByName(name);
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
    public ResponseEntity<Collection<CharacterDoc>> getCharacterLike(@PathVariable String name) {
        List<CharacterDoc> characters = characterService.getCharacterByNameEnglishRegex(name);
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping(value = "all")
    public ResponseEntity<Collection<CharacterDoc>> getAllCharacter() {
        return ResponseEntity.ok().body(characterService.getAllCharacters());
    }

    /**
     * Rest for pageable access of all characters
     * <br>
     * Usage
     * <br>
     * <i>character/page/<b>page=2&size=10</b></i>
     * @param pageable a pageable entity with index , size(default 20) , and sorting params
     * @return a pageable
     */
    @GetMapping(value = "page")
    public ResponseEntity<Page<CharacterDoc>> getAllCharactersPaged(
            @PageableDefault(size = 20)
                    Pageable pageable) {
        return ResponseEntity.ok().body(characterService.getAllCharactersPaged(pageable));
    }

    /**
     * Paged Result of Characters W.R.T Characters::jutsus::length , order DESC
     * <br>
     * Usage
     * <br>
     * <i>character/power/<b>page=2&size=10</b></i>
     * @param pageable a pageable entity with index , size(default 20) , and sorting params
     * @return a pageable
     */
    @GetMapping(value = "power")
    public ResponseEntity<Page<CharacterDoc>> getAllCharactersPagedSortedByJutsusSize(
            @PageableDefault(size = 20)
                    Pageable pageable) {
        return ResponseEntity.ok().body(characterService.getAllCharactersPagedSortedByJutsusSize(pageable));
    }

    /**
     * Finds the characters with ID in name using Regex Match , not indexed like above ,
     * hence can be slow but offers a partial text search due to wildcard matching
     * <br>
     * Usage
     * <br>
     * <i>character/like_paged/<b>uch?size=10&sort=name.english</b></i> : returns all characters with name.english containing <b>uch</b>
     * @param name to match
     * @return Page of characters found
     */
    @GetMapping(value = "like_paged/{name}")
    public ResponseEntity<Page<CharacterDoc>> getCharacterLikePaged(
        @PathVariable String name ,
        @PageableDefault(
            size = 20
        ) Pageable pageable) {
        Page<CharacterDoc> characters = characterService.getCharacterByNameEnglishRegexPaged(name , pageable);
        return ResponseEntity.ok().body(characters);
    }
}