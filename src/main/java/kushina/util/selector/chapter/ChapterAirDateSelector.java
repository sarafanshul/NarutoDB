/*
 * @author Anshul Saraf
 */

package kushina.util.selector.chapter;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import kushina.model.chapter.ChapterAirDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Slf4j
public class ChapterAirDateSelector {

    private ChapterAirDateSelector() {
    }

    public static ChapterAirDate getInfo(Document doc) {

        ChapterAirDate date = new ChapterAirDate();
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

        try {
            Elements elements = doc.select("div[data-source=\"japanese airdate\"] div");
            date.setJapanese(formatter.parse(elements.get(0).text()));

            elements = doc.select("div[data-source=\"english airdate\"] div");
            date.setEnglish(formatter.parse(elements.get(0).text()));
        } catch (ParseException e) {
            log.error("Error in parsing ", e);
        } catch (IndexOutOfBoundsException e) {
            log.error("Index , Date", e);
        }

        return date;
    }

}
