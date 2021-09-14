/*
 * @author Anshul Saraf
 */

package kushina.util.selector.jutsu;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import kushina.model.jutsu.JutsuName;

@Slf4j
public class JutsuNameSelector {
    private JutsuNameSelector() {
    }

    public static JutsuName getNameJutsu(Document doc) {
        //jutsu name
        JutsuName jutsuName = new JutsuName();
        Elements nameElements = doc.select("h2.pi-item.pi-item-spacing.pi-title");

        if (!nameElements.isEmpty()) {
            String english = nameElements.get(0).text();
            if (english.contains("[")) {
                String englishBracket = english.replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim();
                if (englishBracket.contains("edit")) {
                    jutsuName.setEnglish(englishBracket.substring(0, englishBracket.lastIndexOf("edit")));
                } else {
                    jutsuName.setEnglish(english);
                }
            } else if (english.contains("edit")) {
                jutsuName.setEnglish(english.substring(0, english.lastIndexOf("edit")));
            } else {
                jutsuName.setEnglish(english);
            }
            if (nameElements.size() > 1) {
                jutsuName.setKanji(nameElements.get(1).select("rb").text());

                String romaji = nameElements.get(1).select("dfn").text();
                jutsuName.setRomaji(romaji.length() > 1 ? romaji.substring(1, romaji.length() - 1) : "");
            } else {
                jutsuName.setKanji("");
                jutsuName.setRomaji("");
            }
        }

        Elements otherNamesElements = doc.select("div[data-source=\"other names\"]");
        if (!otherNamesElements.isEmpty()) {
            String otherName = otherNamesElements.select("div div").text();
            if (otherName.contains("[")) {
                jutsuName.setOther(otherName.replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            } else {
                jutsuName.setOther(otherName);
            }

        }
        log.info("Jutsu name getted.");
        return jutsuName;
    }
}
