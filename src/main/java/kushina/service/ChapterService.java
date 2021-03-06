/*
 * @author Anshul Saraf
 */

package kushina.service;

import kushina.model.chapter.*;
import kushina.repository.ChapterRepository;
import kushina.util.JsoupConnection;
import kushina.util.selector.chapter.*;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
        if (!doc.select("div.page-header__categories a").text().equals("Episodes")) {
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
            } catch (Exception e) {
                log.error("Exception at chapter Service", e);
            }
        }

        return chapterDoc;
    }

    public Optional<ChapterDoc> getCheckChapterId(String id) {
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

    public ChapterDoc getChapter(String id) {
        Optional<ChapterDoc> obj = getCheckChapterId(id);
        if (!obj.isPresent()) {
            log.warn("Chapter not found.");
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Chapter not found.");
        }
        return obj.get();
    }

    public List<ChapterDoc> getAllChapters() {
        return chapterRepository.findAll();
    }

    public Page<ChapterDoc> getAllChaptersPaged(Pageable pageable) {
        return chapterRepository.findAll(pageable);
    }

    public Page<ChapterDoc> getAllChaptersPagedSorted(Pageable pageable) {
        return chapterRepository.findAllByOrderByEpisodeAbsoluteEpisodeNumberAsc(pageable);
    }

    public List<ChapterDoc> getAllChaptersSorted() {
        return chapterRepository.findAllByOrderByEpisodeAbsoluteEpisodeNumberAsc();
    }

    public Page<ChapterDoc> getAllChaptersCannon(Pageable pageable) {
        return chapterRepository.findAllChaptersCannon(pageable);
    }

    public Page<ChapterDoc> findAllByOrderByEpisodeRangeSorted(int rangeL, int rangeR, boolean cannon, int sortOrder, Pageable pageable) {
        return chapterRepository.findAllByOrderByEpisodeRangeSorted(rangeL, rangeR, cannon, sortOrder, pageable);
    }
}
