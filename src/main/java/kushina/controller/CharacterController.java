/*
 * @author Anshul Saraf
 */

package kushina.controller;

import kushina.model.chapter.ChapterDoc;
import kushina.model.jutsu.JutsuDoc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kushina.model.character.CharacterDoc;
import kushina.service.CharacterService;

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
    @PostMapping(value = "id")
    public ResponseEntity<String> saveCharacter(@RequestParam String id) {
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
    @GetMapping(value = "id")
    public ResponseEntity<CharacterDoc> getCharacter(@RequestParam String id) {
        CharacterDoc character = characterService.getCharacter(id);
        return ResponseEntity.ok().body(character);
    }

    /**
     * Finds the characters with ID in name using match and $text , $search
     *
     * @param name to match
     * @return List of characters found
     */
    @GetMapping(value = "name")
    public ResponseEntity<Collection<CharacterDoc>> getCharactersByName(@RequestParam String name) {
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
    @GetMapping(value = "like")
    public ResponseEntity<Collection<CharacterDoc>> getCharacterLike(@RequestParam String name) {
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
     *
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
     * <b>For Power Sort purposes</b>
     * Paged Result of Characters W.R.T Characters::jutsus::length , order by params
     * <br>
     * Usage
     * <br>
     * <i>character/<b>power?page=1&reverse=true</b></i>
     * <br>
     * <b>reverse=Boolean</b> according to pref
     *
     * @param pageable a pageable entity with index , size(default 20) , and sorting params
     * @param reverse param for desc
     * @return a pageable
     */
    @GetMapping(value = "power")
    public ResponseEntity<Page<CharacterDoc>> getAllCharactersPagedSortedByJutsusSize(
        @PageableDefault(size = 20)
            Pageable pageable ,
        @RequestParam(required = false) Boolean reverse ) {
        if( reverse != null && reverse )
            return ResponseEntity.ok().body(characterService.findAllOrderByJutsusSizeDesc(pageable));
        return ResponseEntity.ok().body(characterService.findAllOrderByJutsusSizeAsc(pageable));
    }

    /**
     * <b>For Search purposes</b>
     * Finds the characters with ID in name using Regex Match , not indexed like above ,
     * hence can be slow but offers a partial text search due to wildcard matching
     * <br>
     * Usage
     * <br>
     * <i>character/like_paged?<b>name=uch?size=10&sort=name.english</b></i> : returns all characters with name.english containing <b>uch</b>
     *
     * @param name to match
     * @return Page of characters found
     */
    @GetMapping(value = "like_paged")
    public ResponseEntity<Page<CharacterDoc>> getCharacterLikePaged(
        @RequestParam String name,
        @PageableDefault(
            size = 20
        ) Pageable pageable) {
        Page<CharacterDoc> characters = characterService.getCharacterByNameEnglishRegexPaged(name, pageable);
        return ResponseEntity.ok().body(characters);
    }

    /**
     * <b>For Alpha/Debut Sort purposes</b>
     * Customizable endpoint  , which returns filtered characters by (name != null , image != null , anime = Naruto & Naruto S)
     * <br>
     * Usage
     * <br>
     * <i>character/core/?sort=name.english,desc/asc</b></i>
     * <br>
     * <i>character/core/?sort=debut.anime,desc/asc</b></i>
     * @param pageable to customize the result
     * @return a Page filtered list of characters paged and sorted (if asked)
     */
    @GetMapping(value = "core")
    public ResponseEntity<Page<CharacterDoc>> getCharacterCore(
        @PageableDefault(
            size = 20
        ) Pageable pageable) {
        Page<CharacterDoc> characters = characterService.findAllFilterByNameEnglishCoreNaruto(pageable);
        return ResponseEntity.ok().body(characters);
    }

    /**
     * Finds all the jutsus of a character filtered by : name.kanji ,range ,type
     * <br>
     * Usage
     * <br>
     * <i>character/<b>jutsus_filtered?id=Hinata_Hyuga</b></i>
     * @param id character id as per DB
     * @return List of Filtered-NotNull Jutsus.
     */
    @GetMapping(value = "jutsus_filtered")
    public ResponseEntity<List<JutsuDoc>> getCharacterJutsusFiltered(
        @RequestParam String id
    ){
        return ResponseEntity.ok(characterService.getCharacterJutsusFiltered(id));
    }

    /**
     * <br>
     * Usage
     * <br>
     * <i>character/<b>debutante?id=Itachi_Uchiha</b></i>
     * @param id character id as per DB
     * @return List of size one, Debut Chapter
     */
    @GetMapping(value = "debutante")
    public ResponseEntity<List<ChapterDoc>> getCharacterDebutChapter(
        @RequestParam String id
    ){
        return ResponseEntity.ok(characterService.getCharacterDebutChapter(id));
    }

}