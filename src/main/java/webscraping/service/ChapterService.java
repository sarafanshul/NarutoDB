package webscraping.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webscraping.model.chapter.*;
import webscraping.model.character.CharacterDoc;
import webscraping.repository.ChapterRepository;
import webscraping.util.JsoupConnection;
import webscraping.util.selector.chapter.*;

@Service
@Slf4j
public class ChapterService {

    @Autowired
    ChapterRepository chapterRepository;

    public CharacterDoc test(String id) {

        Document doc = JsoupConnection.connectionInfo(id);
        log.info("Character url jsoup connected for {} ", id);
        ChapterDoc characterDoc = new ChapterDoc();

        if (doc != null) {
            try {
                ChapterName name = ChapterNameSelector.getInfoName(doc);
                ChapterAirDate date = ChapterAirDateSelector.getInfo(doc);
                ChapterMusic music = ChapterMusicSelector.getInfo(doc);
                ChapterManga manga = ChapterMangaSelector.getInfo(doc);
                ChapterInfo info = ChapterInfoSelector.getInfo(doc);
            }
            catch (Exception e){
                log.error(e.getMessage());
            }
        }

        return new CharacterDoc() ;
    }
}
