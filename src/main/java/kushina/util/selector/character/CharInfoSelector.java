/*
 * @author Anshul Saraf
 */

package kushina.util.selector.character;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import kushina.model.character.CharInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CharInfoSelector {

    private CharInfoSelector() {
    }

    public static CharInfo getInfoBase(Document doc) throws IOException {

        CharInfo charInfo = new CharInfo();

        //description
        Elements descElements = doc.select(".infobox ~ p");
        if (!descElements.isEmpty()) {
            if (!descElements.get(0).text().equals("")) {
                charInfo.setDescription(descElements.get(0).text().trim());
            } else {
                charInfo.setDescription(descElements.get(1).text().trim());
            }
        }
        if (charInfo.getDescription() != null && charInfo.getDescription().contains("[")) {
            charInfo.setDescription(charInfo.getDescription().replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
        }

        //family
        List<String> family = new ArrayList<>();
        Elements familyElements = doc.select("th:containsOwn(Family)");
        if (!familyElements.isEmpty()) {
            for (Element element : familyElements.first().parents().get(1).select("td ul li a")) {
                family.add(element.text().trim());
            }
            charInfo.setFamily(family);
        }

        //nature type
        List<String> natureTypes = new ArrayList<>();
        Elements natureTypeElements = doc.select("th:containsOwn(Nature Type)");
        if (!natureTypeElements.isEmpty()) {
            for (Element element : natureTypeElements.first().parents().get(1).select("td ul li")) {
                String natureType = element.text();
                if (natureType.contains(" (")) {
                    natureTypes.add(natureType.substring(0, natureType.indexOf(" (")).trim());
                } else {
                    natureTypes.add(element.text().trim());
                }
            }
            charInfo.setNatureTypes(natureTypes);
        }

        //unique traits
        List<String> uniqueTraits = new ArrayList<>();
        Elements uniqueTraitsElements = doc.select("th:containsOwn(Unique Traits)");
        if (!uniqueTraitsElements.isEmpty()) {
            for (Element element : uniqueTraitsElements.first().parents().get(1).select("td ul li a")) {
                uniqueTraits.add(element.text().trim());
            }
            charInfo.setUniqueTraits(uniqueTraits);
        }

        //jutsu
        List<String> jutsus = new ArrayList<>();
        Elements jutsuElements = doc.select("th:contains(Jutsu)");
        if (!jutsuElements.isEmpty()) {
            for (Element element : jutsuElements.first().parents().get(1).select(" li a")) {
                jutsus.add(element.text().trim());
            }
            charInfo.setJutsus(jutsus);
        }

        //tools
        List<String> tools = new ArrayList<>();
        Elements toolElements = doc.select("th:contains(Tools)");
        if (!toolElements.isEmpty()) {
            for (Element element : toolElements.first().parents().get(1).select("li a")) {
                tools.add(element.text().trim());
            }
            charInfo.setTools(tools);
        }
        charInfo.setTools(tools);

        // character images
        // return uri instead
        List<String> images = new ArrayList<>();
        Elements imageElements = doc.select(".infobox .image");
        if (!imageElements.isEmpty()) {
            for (Element image : imageElements) {
                String urlImage = image.attr("href");
                images.add(urlImage.substring(0, urlImage.indexOf("/revision")));
            }
            charInfo.setImages(images);
        }

        log.info("Base info getted.");
        return charInfo;
    }
}

