package webscraping.selector.team;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import webscraping.model.team.TeamInfo;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TeamInfoSelector {
    private TeamInfoSelector() {
    }

    public static TeamInfo getInfoTeam(Document doc) {
        TeamInfo teamInfo = new TeamInfo();

        //leaders
        Elements leadersElements = doc.select("th:containsOwn(Leaders)");
        if (!leadersElements.isEmpty()) {
            List<String> leaders = new ArrayList<>();
            for (Element element : leadersElements.first().parent().children().select("td ul li a:not(:has(img))")) {
                leaders.add(element.text().trim());
            }
            teamInfo.setLeaders(leaders);
        }

        //members
        Elements membersElements = doc.select("th:containsOwn(Members)");
        if (!membersElements.isEmpty()) {
            List<String> members = new ArrayList<>();
            for (Element element : membersElements.first().parent().children().select("td ul li a:not(:has(img))")) {
                members.add(element.text().trim());
            }
            teamInfo.setMembers(members);
        }

        //affiliations
        Elements AffiliationsElements = doc.select("th:containsOwn(Affiliations)");
        if (!AffiliationsElements.isEmpty()) {
            List<String> affiliations = new ArrayList<>();
            for (Element element : AffiliationsElements.first().parent().children().select("td ul li a:not(:has(img))")) {
                affiliations.add(element.text().trim());
            }
            teamInfo.setAffiliations(affiliations);
        }
        log.info("Team info getted.");
        return teamInfo;
    }
}
