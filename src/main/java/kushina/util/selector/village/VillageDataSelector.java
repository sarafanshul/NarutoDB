/*
 * @author Anshul Saraf
 */

package kushina.util.selector.village;

import kushina.model.village.VillageData;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class VillageDataSelector {
    private VillageDataSelector() {
    }

    public static VillageData getVillageData(Document doc) throws IOException {
        VillageData villageData = new VillageData();

        //country
        Elements countryElements = doc.select("th:containsOwn(Country)");
        if (!countryElements.isEmpty()) {
            String country = countryElements.first().parent().children().select("td a:not(:has(img))").text();
            villageData.setCountry(country);
        }

        //leader
        Elements leadersElements = doc.select("th:containsOwn(Leader)");
        if (!leadersElements.isEmpty()) {
            List<String> leaders = new ArrayList<>();
            for (Element element : leadersElements.first().parent().children().select("td a:not(:has(img))")) {
                leaders.add(element.text().trim());
            }
            villageData.setLeader(leaders);
        }

        //img symbol
        Elements symbolElements = doc.select("th:containsOwn(Symbol)");
        if (!symbolElements.isEmpty()) {
            String countrySymbol = symbolElements.first().parent().children().select("td a").attr("href");
            villageData.setImgIcon(countrySymbol.substring(0, countrySymbol.indexOf("/revision")));
        }
        log.info("Village data getted.");
        return villageData;
    }
}
