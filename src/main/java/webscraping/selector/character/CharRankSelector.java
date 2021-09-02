package webscraping.selector.character;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import webscraping.model.character.CharRank;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CharRankSelector {
    private CharRankSelector() {
    }

    public static CharRank getInfoRank(Document doc) {
        CharRank charRank = new CharRank();
        //ninja rank
        List<String> ninjaRanks = new ArrayList<>();
        Elements ninjaRankElements = doc.select("th:containsOwn(Ninja Rank)");
        if (!ninjaRankElements.isEmpty()) {
            for (Element element : ninjaRankElements.first().parent().children().select("td ul li")) {
                ninjaRanks.add(element.text().trim());
            }
            charRank.setNinjaRank(ninjaRanks);
        }

        //ninja registration
        Elements ninjaRegElements = doc.select("th:containsOwn(Ninja Registration)");
        if (!ninjaRegElements.isEmpty()) {
            String ninjaRegistration = ninjaRegElements.first().parent().children().get(1).text();
            charRank.setNinjaRegistration(ninjaRegistration);
        }

        //academy graduation age
        Elements academyGradAgeElements = doc.select("th:containsOwn(Academy Grad. Age)");
        if (!academyGradAgeElements.isEmpty()) {
            Integer academyGradAge =
                    Integer.parseInt(academyGradAgeElements.first().parent().children().get(1).text());
            charRank.setAcademyGradAge(academyGradAge);
        }

        //chunin promotion age
        Elements chuninPromAgeElements = doc.select("th:containsOwn(Chūnin Prom. Age)");
        if (!chuninPromAgeElements.isEmpty()) {
            Integer chuninPromAge =
                    Integer.parseInt(chuninPromAgeElements.first().parent().children().get(1).text());
            charRank.setChuninPromAge(chuninPromAge);
        }
        log.info("Anime info getted.");
        return charRank;
    }
}
