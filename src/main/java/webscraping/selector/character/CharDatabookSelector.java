package webscraping.selector.character;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.character.CharDatabook;
import webscraping.model.character.CharDatabookStats;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CharDatabookSelector {
    private CharDatabookSelector() {
    }

    public static List<CharDatabook> getDatabookInfo(Document doc) {
        List<CharDatabook> charDatabookList = new ArrayList<>();
        Elements databookElements = doc.select(".wikitable.center tr");
        if (!databookElements.isEmpty()) {

            //getting infos from first databook
            Elements databookElmtsFirst = databookElements.select("a:containsOwn(First)");
            if (!databookElmtsFirst.isEmpty()) {
                CharDatabook charDatabook = new CharDatabook();
                CharDatabookStats charDatabookStats = new CharDatabookStats();
                charDatabook.setEdition(databookElmtsFirst.text());
                charDatabook.setName(databookElmtsFirst.attr("title").trim());

                Elements statsElmtsFirst =
                        databookElmtsFirst.parents().get(1).getElementsByTag("td");
                charDatabookStats.setNinjutsu(Double.parseDouble(statsElmtsFirst.get(0).text()));
                charDatabookStats.setTaijutsu(Double.parseDouble(statsElmtsFirst.get(1).text()));
                charDatabookStats.setGenjutsu(Double.parseDouble(statsElmtsFirst.get(2).text()));
                charDatabookStats.setIntelligence(Double.parseDouble(statsElmtsFirst.get(3).text()));
                charDatabookStats.setStrength(Double.parseDouble(statsElmtsFirst.get(4).text()));
                charDatabookStats.setSpeed(Double.parseDouble(statsElmtsFirst.get(5).text()));
                charDatabookStats.setStamina(Double.parseDouble(statsElmtsFirst.get(6).text()));
                charDatabookStats.setHandSeals(Double.parseDouble(statsElmtsFirst.get(7).text()));
                charDatabookStats.setTotal(Double.parseDouble(statsElmtsFirst.get(8).text()));

                charDatabook.setStats(charDatabookStats);
                charDatabookList.add(charDatabook);
            }

            //getting infos from second databook
            Elements databookElmtsSec = databookElements.select("a:containsOwn(Second)");
            if (!databookElmtsSec.isEmpty()) {
                CharDatabook charDatabook = new CharDatabook();
                CharDatabookStats charDatabookStats = new CharDatabookStats();
                charDatabook.setEdition(databookElmtsSec.text());
                charDatabook.setName(databookElmtsSec.attr("title").trim());

                Elements statsElmtsSec =
                        databookElmtsSec.parents().get(1).getElementsByTag("td");
                charDatabookStats.setNinjutsu(Double.parseDouble(statsElmtsSec.get(0).text()));
                charDatabookStats.setTaijutsu(Double.parseDouble(statsElmtsSec.get(1).text()));
                charDatabookStats.setGenjutsu(Double.parseDouble(statsElmtsSec.get(2).text()));
                charDatabookStats.setIntelligence(Double.parseDouble(statsElmtsSec.get(3).text()));
                charDatabookStats.setStrength(Double.parseDouble(statsElmtsSec.get(4).text()));
                charDatabookStats.setSpeed(Double.parseDouble(statsElmtsSec.get(5).text()));
                charDatabookStats.setStamina(Double.parseDouble(statsElmtsSec.get(6).text()));
                charDatabookStats.setHandSeals(Double.parseDouble(statsElmtsSec.get(7).text()));
                charDatabookStats.setTotal(Double.parseDouble(statsElmtsSec.get(8).text()));

                charDatabook.setStats(charDatabookStats);
                charDatabookList.add(charDatabook);
            }

            //getting infos from third databook
            Elements databookElmtsThird = databookElements.select("a:containsOwn(Third)");
            if (!databookElmtsThird.isEmpty()) {
                CharDatabook charDatabook = new CharDatabook();
                CharDatabookStats charDatabookStats = new CharDatabookStats();
                charDatabook.setEdition(databookElmtsThird.text());
                charDatabook.setName(databookElmtsThird.attr("title").trim());

                Elements statsElmtsThird =
                        databookElmtsThird.parents().get(1).getElementsByTag("td");
                charDatabookStats.setNinjutsu(Double.parseDouble(statsElmtsThird.get(0).text()));
                charDatabookStats.setTaijutsu(Double.parseDouble(statsElmtsThird.get(1).text()));
                charDatabookStats.setGenjutsu(Double.parseDouble(statsElmtsThird.get(2).text()));
                charDatabookStats.setIntelligence(Double.parseDouble(statsElmtsThird.get(3).text()));
                charDatabookStats.setStrength(Double.parseDouble(statsElmtsThird.get(4).text()));
                charDatabookStats.setSpeed(Double.parseDouble(statsElmtsThird.get(5).text()));
                charDatabookStats.setStamina(Double.parseDouble(statsElmtsThird.get(6).text()));
                charDatabookStats.setHandSeals(Double.parseDouble(statsElmtsThird.get(7).text()));
                charDatabookStats.setTotal(Double.parseDouble(statsElmtsThird.get(8).text()));

                charDatabook.setStats(charDatabookStats);
                charDatabookList.add(charDatabook);
            }
        }
        log.info("Databook info getted.");
        return charDatabookList;
    }

}
