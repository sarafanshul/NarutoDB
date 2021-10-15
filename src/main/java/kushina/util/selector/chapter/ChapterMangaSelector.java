/*
 * @author Anshul Saraf
 */

package kushina.util.selector.chapter;

import kushina.model.chapter.ChapterManga;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Slf4j
public class ChapterMangaSelector {

    private ChapterMangaSelector() {
    }

    public static ChapterManga getInfo(Document doc) {

        ChapterManga chapterManga = new ChapterManga();
        try {
            Elements elem = doc.select("div[data-source=\"chapters\"] div");
            chapterManga.setChapters(elem.eachText());
        } catch (Exception e) {
            log.error("Unknown Exception , Chapter manga", e);
        }

        return chapterManga;
    }

}
