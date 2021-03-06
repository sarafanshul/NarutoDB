/*
 * @author Anshul Saraf
 */

package kushina.util.selector.team;

import kushina.model.team.TeamInfoBase;
import kushina.util.Converters;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TeamInfoBaseSelector {

    private TeamInfoBaseSelector() {
    }

    public static TeamInfoBase getTeamInfoBase(Document doc) throws IOException {
        TeamInfoBase teamInfoBase = new TeamInfoBase();

        //description
        Elements descElements = doc.select(".infobox ~ p");
        if (!descElements.isEmpty()) {
            if (!descElements.get(0).text().equals("")) {
                teamInfoBase.setDescription(descElements.get(0).text().trim());
            } else {
                teamInfoBase.setDescription(descElements.get(1).text().trim());
            }
            if (teamInfoBase.getDescription().contains("[")) {
                teamInfoBase.setDescription(teamInfoBase.getDescription().replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            }
        }

        //image
        Elements imageElements = doc.select(".infobox .image");
        if (!imageElements.isEmpty()) {
            String urlImage = imageElements.attr("href");
            teamInfoBase.setImage(Converters.getBase64Image(urlImage.substring(0, urlImage.indexOf("/revision")) +
                "/revision" +
                "/latest/scale-to-width-down/300"));
        }

        //jutsus
        Elements jutsuElements = doc.select(".box .cellbox");
        if (!jutsuElements.isEmpty()) {
            List<String> jutsus = new ArrayList<>();
            for (Element element : jutsuElements.select("tbody tr td ul li a")) {
                jutsus.add(element.text().trim());
            }
            teamInfoBase.setTeamJutsus(jutsus);
        }

        log.info("Team info base getted.");
        return teamInfoBase;
    }
}
