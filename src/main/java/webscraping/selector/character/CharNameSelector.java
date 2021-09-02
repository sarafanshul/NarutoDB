package webscraping.selector.character;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.character.CharName;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CharNameSelector {

    private CharNameSelector() {
    }

    public static CharName getInfoName(Document doc) {
        CharName charName = new CharName();

        //english name
        Elements nameEnElements = doc.select(".mainheader");
        if (!nameEnElements.isEmpty()) {
            charName.setEnglish(nameEnElements.textNodes().get(0).toString().trim());
        }

        //names kanji & kana and romaji name
        Elements nameElements = doc.select(".title");
        if (!nameElements.isEmpty()) {
            String unionNames = nameElements.get(0).text();
            charName.setKanji(unionNames.substring(0, unionNames.indexOf(" ")).trim());
            charName.setRomaji(unionNames.substring(unionNames.indexOf(" ")).trim());
        }

        //other names
        Elements otherNameElements = doc.select(".mainheader ul li");
        List<String> otherNames = new ArrayList<>();
        if (!otherNameElements.isEmpty()) {
            List<String> otherNamesSeparated = otherNameElements.eachText();
            for (String otherName : otherNamesSeparated) {
                if (otherName.contains("[")) {
                    String otherNameBracket =
                            otherName.replaceAll("[0-9]", "").replace("[", "").replace("]", "");
                    if (otherNameBracket.contains(") ")) {
                        otherNames.add(otherNameBracket.substring(0, otherNameBracket.lastIndexOf(") ") + 1).trim());
                        otherNames.add(otherNameBracket.substring(otherNameBracket.lastIndexOf(") ") + 1).trim());
                    } else {
                        otherNames.add(otherNameBracket);
                    }
                } else {
                    otherNames.add(otherName.trim());
                }
            }
            charName.setOthers(otherNames);
        }
        log.info("Name info getted.");
        return charName;
    }
}
