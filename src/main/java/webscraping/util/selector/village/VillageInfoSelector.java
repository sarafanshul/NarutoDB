package webscraping.util.selector.village;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.village.VillageInfo;
import webscraping.util.Converters;

import java.io.IOException;

@Slf4j
public class VillageInfoSelector {
    private VillageInfoSelector() {
    }

    public static VillageInfo getInfoVillage(Document doc) throws IOException {
        VillageInfo villageInfo = new VillageInfo();

        //description
        Elements descElements = doc.select(".infobox ~ p");
        if (!descElements.isEmpty()) {
            if (!descElements.get(0).text().equals("")) {
                villageInfo.setDescription(descElements.get(0).text().trim());
            } else {
                villageInfo.setDescription(descElements.get(1).text().trim());
            }
            if (villageInfo.getDescription().contains("[")) {
                villageInfo.setDescription(villageInfo.getDescription().replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            }
        }

        //image
        Elements imageElements = doc.select(".infobox .image");
        if (!imageElements.isEmpty()) {
            String urlImage = imageElements.attr("href");
            villageInfo.setImage(Converters.getBase64Image(urlImage.substring(0, urlImage.indexOf("/revision")) +
                "/revision" +
                "/latest/scale-to-width-down/300"));
        }
        log.info("Village base info getted.");
        return villageInfo;
    }
}
