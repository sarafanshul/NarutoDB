package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webscraping.service.ChapterService;

@Slf4j
@RestController
@RequestMapping(value = "chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @PostMapping(value = "id/{id}")
    public ResponseEntity<Void> getCharacter(@PathVariable String id) {
        chapterService.insert(id);
        return ResponseEntity.ok().build();
    }

}
