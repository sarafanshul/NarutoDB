package webscraping.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import webscraping.model.chapter.*;
import webscraping.repository.ChapterRepository;
import webscraping.util.JsoupConnection;
import webscraping.util.selector.chapter.*;

import java.util.Optional;

@Service
@Slf4j
public class ChapterService {

    @Autowired
    ChapterRepository chapterRepository;

    public ChapterDoc getChapterInfo(String id) {

        Document doc = JsoupConnection.connectionInfo(id);
        ChapterDoc chapterDoc = new ChapterDoc();

        // a ambiguous reference
        if(!doc.select("div.page-header__categories a").text().equals("Episodes")){
            doc = JsoupConnection.connectionInfo(id + "_(episode)");
            log.info("new url : " + id + "_(episode)");
        }

        if (doc != null) {
            try {
                ChapterName name = ChapterNameSelector.getInfoName(doc);
                ChapterAirDate date = ChapterAirDateSelector.getInfo(doc);
                ChapterMusic music = ChapterMusicSelector.getInfo(doc);
                ChapterManga manga = ChapterMangaSelector.getInfo(doc);
                ChapterInfo info = ChapterInfoSelector.getInfo(doc);
                ChapterEpisode episode = ChapterEpisodeSelector.getInfo(doc);

                chapterDoc.setId(id);
                chapterDoc.setName(name);
                chapterDoc.setEpisode(episode);
                chapterDoc.setDescription(info.getDescription());
                chapterDoc.setImages(info.getImages());
                chapterDoc.setArc(info.getArc());
                chapterDoc.setManga(manga);
                chapterDoc.setMusic(music);
                chapterDoc.setDate(date);
            }
            catch (Exception e){
                log.error("Exception at chapter Service" , e);
            }
        }

        return chapterDoc ;
    }

    public Optional<ChapterDoc> getCheckChapterId( String id ){
        return chapterRepository.findById(id);
    }

    public ChapterDoc insert(String id) {
        if (!getCheckChapterId(id).isPresent()) { //check if character already
            // not exists
            log.warn("{} , Chapter inserted.", id);
            return chapterRepository.insert(getChapterInfo(id));
        } else {
            log.warn("Chapter already exists.");
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Chapter already exists.");
        }
    }
}
