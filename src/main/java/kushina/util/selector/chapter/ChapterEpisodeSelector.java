/*
 * @author Anshul Saraf
 */

package kushina.util.selector.chapter;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import kushina.model.chapter.ChapterEpisode;

@Slf4j
public class ChapterEpisodeSelector {

    private ChapterEpisodeSelector() {
    }

    public static ChapterEpisode getInfo(Document doc) {
        ChapterEpisode episode = new ChapterEpisode();

        Element section = doc.selectFirst("section.pi-item.pi-group.pi-border-color");

        // current episode
        String cur = section.select("h3:contains(Episode) ~ div").text();
        if (cur.contains("(")) {
            cur = cur.substring(0, cur.indexOf("("));
        }

        episode.setSeries(cur.substring(0, cur.indexOf('#')).trim());
        episode.setEpisode(Double.valueOf(cur.substring(cur.indexOf('#') + 1)));

        // previous episode name
        String prev = section.select("h3:contains(Previous) ~ div").text().replace("\"", "");

        episode.setPrevious(prev);

        // next episode name
        String next = section.select("h3:contains(Next) ~ div").text().replace("\"", "");

        episode.setNext(next);

        // season
        episode.setSeason(getSeason(episode.getSeries()));

        // episode number from 1st release
        episode.setAbsoluteEpisodeNumber(getAbsoluteEpisodeNumber(
            episode.getSeason(),
            episode.getEpisode()
        ));

        return episode;
    }

    public static int getSeason(String seriesName) {
        if (seriesName.equals("Naruto"))
            return 1;
        if (seriesName.equals("Naruto: Shippūden"))
            return 2;
        if (seriesName.equals("Boruto"))
            return 3;
        throw new IllegalArgumentException("Unknown series to be seasoned!");
    }

    public static double getAbsoluteEpisodeNumber(int season, double episode) {
        switch (season) {
            case 1:
                return episode;
            case 2:
                return 220 + episode;
            case 3:
                return 500 + 220 + episode;
        }
        throw new IllegalArgumentException("Unknown season for creating id");
    }

}
