package webscraping.selector.team;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import webscraping.model.team.TeamName;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TeamNameSelector {
    private TeamNameSelector() {
    }

    public static TeamName getTeamName(Document doc) {
        TeamName teamName = new TeamName();

        //english name
        Elements englishElements = doc.getElementsByClass("mainheader");
        if (!englishElements.isEmpty()) {
            String englishName = englishElements.get(0).text().trim();
            if (englishName.contains("[")) {
                teamName.setEnglish(englishName.replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            } else {
                teamName.setEnglish(englishName.trim());
            }
        }

        //kanji name
        Elements kanjiElmts = doc.select("span[lang=\"ja\"]");
        if (!kanjiElmts.isEmpty()) {
            String kanjiName = kanjiElmts.get(0).text().trim();
            if (kanjiName.contains("[")) {
                teamName.setKanji(kanjiName.replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            } else {
                teamName.setKanji(kanjiName.trim());
            }
        }

        //other name
        Elements otherElmts = doc.getElementsByClass("mainheader");
        if (!otherElmts.isEmpty()) {
            List<String> others = new ArrayList<>();
            Elements otherElmtsSelect = otherElmts.get(1).select("td");
            if (!otherElmtsSelect.select("td p").isEmpty()) {
                for (Element element : otherElmtsSelect.select("td p")) {
                    if (element.text().contains("[")) {
                        String otherReplaced = element.text().trim();
                        others.add(otherReplaced.substring(0, otherReplaced.indexOf("[")));
                    } else {
                        others.add(element.text());
                    }
                }
            }
            if (!otherElmtsSelect.select("td span").isEmpty()) {
                for (Element element : otherElmtsSelect.select("td > span")) {
                    if (element.text().contains("[")) {
                        String otherReplaced = element.text().trim();
                        others.add(otherReplaced.substring(0, otherReplaced.indexOf("[")));
                    } else {
                        others.add(element.text());
                    }
                }
            }
            if (!otherElmtsSelect.select("td ul li").isEmpty()) {
                for (Element element : otherElmtsSelect.select("td ul li")) {
                    if (element.text().contains("[")) {
                        String otherReplaced = element.text().trim();
                        others.add(otherReplaced.substring(0, otherReplaced.indexOf("[")));
                    } else {
                        others.add(element.text());
                    }
                }
            }

            teamName.setOthers(others);
        }
        log.info("Team name info getted.");
        return teamName;
    }

}
