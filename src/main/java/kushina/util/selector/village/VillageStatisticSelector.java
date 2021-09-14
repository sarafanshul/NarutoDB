/*
 * @author Anshul Saraf
 */

package kushina.util.selector.village;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import kushina.model.village.VillageStatistic;

@Slf4j
public class VillageStatisticSelector {
    private VillageStatisticSelector() {
    }

    public static VillageStatistic getVillageStatistic(Document doc) {
        VillageStatistic villageStatistic = new VillageStatistic();

        //population
        Elements populationElements = doc.select("th:containsOwn(Population)");
        if (!populationElements.isEmpty()) {
            String population = populationElements.first().parent().children().select("td").text();
            villageStatistic.setPopulation(population);
        }

        //military
        Elements militaryElements = doc.select("th:containsOwn(Military)");
        if (!militaryElements.isEmpty()) {
            String military = militaryElements.first().parent().children().select("td").text();
            villageStatistic.setMilitary(military);
        }

        //economy
        Elements economyElements = doc.select("th:containsOwn(Economy)");
        if (!economyElements.isEmpty()) {
            String economy = economyElements.first().parent().children().select("td").text();
            villageStatistic.setEconomy(economy);
        }
        log.info("Village statistic info getted.");
        return villageStatistic;
    }
}
