/*
 * @author Anshul Saraf
 */

package kushina.util.selector.chapter;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import kushina.model.chapter.ChapterInfo;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ChapterInfoSelector {

    private ChapterInfoSelector() {
    }

    public static ChapterInfo getInfo(Document doc) {

        ChapterInfo info = new ChapterInfo();

        // description
        try {
            Elements elem = doc.select("#Synopsis").parents().select("p");
            StringBuilder description = new StringBuilder();
            for (Element e : elem) {
                description.append(e.text()).append('\n');
            }
            info.setDescription(description.toString().trim());
        } catch (NullPointerException e) {
            log.error("Null Pointer , Info-description", e);
        }

        // arc
        try {
            Elements arc = doc.select("div[data-source=\"arc\"] div");
            info.setArc(arc.text());
        } catch (NullPointerException e) {
            log.error("Null Pointer , Info", e);
        }

        // character images
        // return uri instead
        List<String> images = new ArrayList<>();
        Elements imageElements = doc.select(".image.image-thumbnail");
        if (!imageElements.isEmpty()) {
            for (Element image : imageElements) {
                String urlImage = image.attr("href");
                images.add(urlImage.substring(0, urlImage.indexOf("/revision")));
            }
            info.setImages(images);
        }

        return info;
    }

}
