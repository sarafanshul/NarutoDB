package webscraping.selector.jutsu;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import webscraping.model.jutsu.JutsuInfo;
import webscraping.util.Converters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class JutsuInfoSelector {
    private JutsuInfoSelector() {
    }

    public static JutsuInfo getInfoJutsu(Document doc) throws IOException {
        JutsuInfo jutsuInfo = new JutsuInfo();

        //description
        Elements descElements = doc.select(".infobox ~ p");
        if (!descElements.isEmpty()) {
            if (!descElements.get(0).text().equals("")) {
                jutsuInfo.setDescription(descElements.get(0).text().trim());
            } else {
                jutsuInfo.setDescription(descElements.get(1).text().trim());
            }
        }
        if (jutsuInfo.getDescription().contains("[")) {
            jutsuInfo.setDescription(jutsuInfo.getDescription().replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
        }

        //image
        Elements imgElements = doc.select("aside figure a");
        if (!imgElements.isEmpty()) {
            String urlImage = imgElements.attr("href");
            jutsuInfo.setImage(Converters.getBase64Image(urlImage.substring(0, urlImage.indexOf("/revision")) +
                    "/revision" +
                    "/latest/scale-to-width-down/300"));
        }

        //classification
        Elements infoElements = doc.select("div[data-source=\"jutsu classification\"]");
        if (!infoElements.isEmpty()) {
            List<String> classificationList = new ArrayList<>();
            for (Element infoElement : infoElements.select("div a")) {
                classificationList.add(infoElement.attr("title").trim());
            }
            jutsuInfo.setClassification(classificationList);
        }

        //nature
        Elements natureElements = doc.select("div[data-source=\"jutsu type\"]");
        if (!natureElements.isEmpty()) {
            jutsuInfo.setNature(natureElements.select("ul li a").get(1).text().trim());
        }

        //rank
        Elements rankElements = doc.select("div[data-source=\"jutsu rank\"]");
        if (!rankElements.isEmpty()) {
            String rank = rankElements.select("div div").first().text();
            jutsuInfo.setRank((rank.contains("-rank")) ? rank.substring(0, rank.indexOf("-rank")).trim() : rank.trim());
        }

        //class type
        Elements classElements = doc.select("div[data-source=\"jutsu class type\"]");
        if (!classElements.isEmpty()) {
            String[] typeJutsu = classElements.select("div div").first().text().split(", ");
            List<String> typeList = new ArrayList<>(Arrays.asList(typeJutsu));
            jutsuInfo.setType(typeList);
        }

        //range
        Elements rangeElements = doc.select("div[data-source=\"jutsu range\"]");
        if (!rangeElements.isEmpty()) {
            jutsuInfo.setRange(rangeElements.select("div div").first().text().trim());
        }

        //hand seals
        Elements handSealsElements = doc.select("div[data-source=\"hand signs\"]");
        if (!handSealsElements.isEmpty()) {
            if (handSealsElements.select("div div").text().contains("[")) {
                String handSeal = handSealsElements.select("div div").text().replaceAll("[0-9]", "").replace("[", "");
                jutsuInfo.setHandSeals(handSeal.replace("]", "").trim());
            } else {
                jutsuInfo.setHandSeals(handSealsElements.select("div div").text().trim());
            }
        }

        //jutsu users
        Elements userElements = doc.select("div[data-source=\"users\"]");
        if (!userElements.isEmpty()) {
            List<String> users = new ArrayList<>();

            for (Element element : userElements.select("li a")) {
                users.add(element.text().trim());
            }
            jutsuInfo.setUsers(users);
        }
        log.info("Info jutsu getted.");
        return jutsuInfo;
    }
}
