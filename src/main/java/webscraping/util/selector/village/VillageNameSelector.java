package webscraping.util.selector.village;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.village.VillageName;

@Slf4j
public class VillageNameSelector {
    private VillageNameSelector() {
    }

    public static VillageName getVillageName(Document doc) {
        VillageName villageName = new VillageName();

        //english name
        Elements englishElements = doc.getElementsByClass("mainheader");
        if (!englishElements.isEmpty()) {
            String englishName = englishElements.get(0).text().trim();
            if (englishName.contains("[")) {
                villageName.setEnglish(englishName.replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            } else {
                villageName.setEnglish(englishName.trim());
            }
        }

        //kanji name
        Elements kanjiElmts = doc.select("span[lang=\"ja\"]");
        if (!kanjiElmts.isEmpty()) {
            String kanjiName = kanjiElmts.get(0).text().trim();
            if (kanjiName.contains("[")) {
                villageName.setKanji(kanjiName.replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            } else {
                villageName.setKanji(kanjiName.trim());
            }
        }

        //romaji name
        Elements romajiElmts = doc.select("th:containsOwn(Rōmaji)");
        if (!romajiElmts.isEmpty()) {
            String romajiName = romajiElmts.first().parent().children().select("td").text().trim();
            if (romajiName.contains("[")) {
                villageName.setRomaji(romajiName.replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            } else {
                villageName.setRomaji(romajiName.trim());
            }
        }

        log.info("Village name info getted.");
        return villageName;
    }
}
