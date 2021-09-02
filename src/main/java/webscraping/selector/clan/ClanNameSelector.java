package webscraping.selector.clan;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.clan.ClanName;

@Slf4j
public class ClanNameSelector {
    private ClanNameSelector() {
    }

    public static ClanName getNameClan(Document doc) {
        ClanName clanName = new ClanName();

        //english name
        Elements englishElements = doc.getElementsByClass("mainheader");
        if (!englishElements.isEmpty()) {
            clanName.setEnglish(englishElements.get(0).text().trim());
        }

        //kanji and romaji names
        Elements kanjiRomajiElmts = doc.select("span[lang=\"ja\"]");
        if (!kanjiRomajiElmts.isEmpty()) {
            clanName.setKanji(kanjiRomajiElmts.get(0).text().trim());
            clanName.setRomaji(kanjiRomajiElmts.parents().first().getElementsByTag("i").text().trim());
        }
        log.info("Name info getted.");
        return clanName;
    }
}
