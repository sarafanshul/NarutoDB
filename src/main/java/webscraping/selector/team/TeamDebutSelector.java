package webscraping.selector.team;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.team.TeamAnime;
import webscraping.model.team.TeamDebut;
import webscraping.model.team.TeamManga;

import java.util.ArrayList;
import java.util.List;

import static webscraping.util.TeamInfoCheckNull.checkNullInfoAnime;
import static webscraping.util.TeamInfoCheckNull.checkNullManga;

@Slf4j
public class TeamDebutSelector {
    private TeamDebutSelector() {
    }

    public static TeamDebut getTeamDebut(Document doc) {
        TeamDebut teamDebut = new TeamDebut();

        TeamManga teamManga = new TeamManga();
        TeamAnime teamAnime = new TeamAnime();

        //manga
        Elements mangaElements = doc.select("th:contains(Manga)");
        if (!mangaElements.isEmpty()) {
            if (mangaElements.toString().contains("Viz manga")) {
                teamManga.setName(mangaElements.get(1).parents().first().select("td a i").text().trim());
                String volume = mangaElements.get(1).parents().first().select("td a").get(0).text().trim();
                teamManga.setVolume(Double.parseDouble(volume.substring(volume.indexOf("#") + 1)));
                String chapter = mangaElements.get(1).parents().first().select("td a").get(1).text().trim();
                teamManga.setChapter(Double.parseDouble(chapter.substring(chapter.indexOf("#") + 1)));
            } else {
                teamManga.setName(mangaElements.get(0).parents().first().select("td a i").text().trim());
                String volume = mangaElements.get(0).parents().first().select("td a").get(0).text().trim();
                teamManga.setVolume(Double.parseDouble(volume.substring(volume.indexOf("#") + 1)));
                String chapter = mangaElements.get(0).parents().first().select("td a").get(1).text().trim();
                teamManga.setChapter(Double.parseDouble(chapter.substring(chapter.indexOf("#") + 1)));
            }

        }
        teamDebut.setManga(checkNullManga(teamManga) ? null : teamManga);

        //anime
        Elements animeElements = doc.select("th:containsOwn(Anime)");
        if (!animeElements.isEmpty()) {
            String anime = animeElements.first().parent().children().get(1).text();
            teamAnime.setName(animeElements.first().parent().select("tr td i").text().trim());
            teamAnime.setEpisode(Double.parseDouble(anime.substring(anime.indexOf("#") + 1)));
        }
        teamDebut.setAnime(checkNullInfoAnime(teamAnime) ? null : teamAnime);

        //novel
        Elements novelElements = doc.select("th:containsOwn(Novel)");
        if (!novelElements.isEmpty()) {
            String novel = novelElements.first().parent().children().get(1).text();
            if (novel.contains("(")) {
                teamDebut.setNovel(novel.substring(0, novel.indexOf(" (")).trim());
            } else {
                teamDebut.setNovel(novel);
            }
        }

        //movie
        Elements movieElements = doc.select("th:containsOwn(Movie)");
        if (!movieElements.isEmpty()) {
            String movie = movieElements.first().parent().children().get(1).text();
            if (movie.contains("(")) {
                teamDebut.setMovie(movie.substring(0, movie.indexOf(" (")).trim());
            } else {
                teamDebut.setMovie(movie);
            }
        }

        //game
        Elements gameElements = doc.select("th:containsOwn(Game)");
        if (!gameElements.isEmpty()) {
            String game = gameElements.first().parent().children().get(1).text();
            if (game.contains("(")) {
                teamDebut.setGame(game.substring(0, game.indexOf(" (")).trim());
            } else {
                teamDebut.setGame(game);
            }
        }

        //ova
        Elements ovaElements = doc.select("th:containsOwn(OVA)");
        if (!ovaElements.isEmpty()) {
            String ova = ovaElements.first().parent().children().get(1).text();
            if (ova.contains("(")) {
                teamDebut.setOva(ova.substring(0, ova.indexOf(" (")).trim());
            } else {
                teamDebut.setOva(ova);
            }
        }

        //appears
        Elements appearsElements = doc.select("th:containsOwn(Appears in)");
        List<String> appears = new ArrayList<>();
        if (!appearsElements.isEmpty()) {
            String[] appearsElemSeparated = appearsElements.first().parent().children().get(1).text().split(", ");
            for (String appear : appearsElemSeparated) {
                appears.add(appear.trim().trim());
            }
            teamDebut.setAppears(appears);
        }
        log.info("Team debut info getted.");
        return teamDebut;
    }
}
