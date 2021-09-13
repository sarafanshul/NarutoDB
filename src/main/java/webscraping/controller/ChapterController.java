package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webscraping.model.character.CharacterDoc;
import webscraping.service.ChapterService;

@Slf4j
@RestController
@RequestMapping(value = "chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @GetMapping(value = "test/{id}")
    public ResponseEntity<CharacterDoc> getCharacter(@PathVariable String id) {
        log.info(id);
        CharacterDoc character = chapterService.test(id);
        return ResponseEntity.ok().body(character);
    }

}
