/*
 * @author Anshul Saraf
 */

package kushina.util.selector.clan;

import kushina.model.clan.ClanInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ClanInfoSelector {
    private ClanInfoSelector() {
    }

    public static ClanInfo getInfoData(Document doc) throws IOException {
        ClanInfo clanInfo = new ClanInfo();

        //description
        Elements descElements = doc.select(".mw-parser-output > p");
        if (!descElements.isEmpty()) {
            if (!descElements.get(0).text().equals("")) {
                clanInfo.setDescription(descElements.get(0).text().trim());
            } else if (!descElements.get(1).text().equals("")) {
                clanInfo.setDescription(descElements.get(1).text().trim());
            } else {
                clanInfo.setDescription(descElements.get(2).text().trim());
            }
            if (clanInfo.getDescription().contains("[")) {
                clanInfo.setDescription(clanInfo.getDescription().replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            }
        }

        //image
        Elements imageElements = doc.select("tr td.imagecell a");
        if (!imageElements.isEmpty()) {
            String urlImage = imageElements.attr("href");
            clanInfo.setImage(urlImage.substring(0, urlImage.indexOf("/revision")));
        }

        //appears
        Elements appearsElements = doc.select("th:containsOwn(Appears in)");
        if (!appearsElements.isEmpty()) {
            String[] appearsArray = appearsElements.parents().first().select("td").text().split(", ");
            List<String> appearsList = new ArrayList<>();
            for (String appear : appearsArray) {
                appearsList.add(appear.trim());
            }
            clanInfo.setAppears(appearsList);
        }

        //affiliation
        Elements affiliationElmts = doc.select("th:containsOwn(Affiliation)");
        List<String> affiliationList = new ArrayList<>();
        if (!affiliationElmts.isEmpty()) {
            for (Element element : affiliationElmts.parents().first().select("ul li a:not(:has(img))")) {
                affiliationList.add(element.text().trim());
            }
            clanInfo.setAffiliation(affiliationList);
        }

        //kekkegenkai
        Elements kekkeiElements = doc.select("th:containsOwn(Kekkei Genkai)");
        List<String> kekkeiList = new ArrayList<>();
        if (!kekkeiElements.isEmpty()) {
            for (Element element : kekkeiElements.parents().first().select("ul li a:not(:has(img))")) {
                kekkeiList.add(element.text().trim());
            }
            clanInfo.setKekkeiGenkai(kekkeiList);
        }

        //members
        Elements membersElements = doc.select("th:containsOwn(Known Members)");
        List<String> members = new ArrayList<>();
        if (!membersElements.isEmpty()) {
            for (Element element : membersElements.parents().get(1).select("ul li a")) {
                members.add(element.text().trim());
            }
            clanInfo.setMembers(members);
        }

        //jutsus
        Elements jutsusElements = doc.select("th:containsOwn(Jutsu)");
        List<String> jutsus = new ArrayList<>();
        if (!jutsusElements.isEmpty()) {
            for (Element element : jutsusElements.parents().get(1).select("ul li a")) {
                jutsus.add(element.text().trim());
            }
            clanInfo.setJutsus(jutsus);
        }
        log.info("Clan anime info getted.");
        return clanInfo;
    }
}
