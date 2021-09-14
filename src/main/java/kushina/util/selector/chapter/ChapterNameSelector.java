/*
 * @author Anshul Saraf
 */

package kushina.util.selector.chapter;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import kushina.model.chapter.ChapterName;

@Slf4j
public class ChapterNameSelector {

    private ChapterNameSelector() {
    }

    public static ChapterName getInfoName(Document doc) {
        ChapterName chapterName = new ChapterName();

        Elements nameElements = doc.select("h2.pi-item.pi-item-spacing.pi-title.pi-secondary-background");

        if (nameElements.size() > 0) {
            try {
                //english name
                String englishName = nameElements.get(0).text();
                chapterName.setEnglish(englishName.split("\"")[1]);

                chapterName.setKanji(nameElements.get(1).select("rb").text());

                chapterName.setRomaji(nameElements.get(1).select("dfn").text().replaceAll("[\\[\\](){}]", ""));
            } catch (IndexOutOfBoundsException e) {
                log.error("Index , Name", e);
            }
        }

        return chapterName;
    }
}
