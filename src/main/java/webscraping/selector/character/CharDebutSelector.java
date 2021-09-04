package webscraping.selector.character;

import com.google.common.base.CharMatcher;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.character.CharAnime;
import webscraping.model.character.CharDebut;
import webscraping.model.character.CharManga;

import java.util.ArrayList;
import java.util.List;

import static webscraping.util.CharacterInfoCheckNull.checkNullInfoAnime;
import static webscraping.util.CharacterInfoCheckNull.checkNullInfoManga;

@Slf4j
public class CharDebutSelector {
    private CharDebutSelector() {
    }

    public static CharDebut getInfoDebut(Document doc) {
        CharDebut charDebut = new CharDebut();
        CharManga charManga = new CharManga();
        CharAnime charAnime = new CharAnime();

        //manga
        Elements mangaElements = doc.select("th:containsOwn(Manga)");
        if (!mangaElements.isEmpty()) {
            charManga.setName(mangaElements.first().parent().children().select("td a i").text().trim());
            String volume = mangaElements.first().parent().children().select("td a").get(0).text().trim();
            charManga.setVolume(Double.parseDouble(volume.substring(volume.indexOf("#") + 1)));
            String chapter = "#00";
            try {
                chapter = mangaElements.first().parent().children().select("td a").get(1).text().trim();
            } catch (IndexOutOfBoundsException ignored) {
            }
            charManga.setChapter(Double.parseDouble(chapter.substring(chapter.indexOf("#") + 1)));
        }
        charDebut.setManga(checkNullInfoManga(charManga) ? null : charManga);

        //anime
        Elements animeElements = doc.select("th:containsOwn(Anime)");
        if (!animeElements.isEmpty()) {
            String anime = animeElements.first().parent().children().get(1).text();
            charAnime.setName(animeElements.first().parent().select("tr td i").text().trim());
            charAnime.setEpisode(Double.parseDouble(
                    CharMatcher.inRange('0', '9').retainFrom(anime.substring(anime.indexOf("#") + 1))
            ));
        }
        charDebut.setAnime(checkNullInfoAnime(charAnime) ? null : charAnime);

        //novel
        Elements novelElements = doc.select("th:containsOwn(Novel)");
        if (!novelElements.isEmpty()) {
            String novel = novelElements.first().parent().children().get(1).text();
            if (novel.contains("(")) {
                charDebut.setNovel(novel.substring(0, novel.indexOf(" (")).trim());
            } else {
                charDebut.setNovel(novel);
            }
        }

        //movie
        Elements movieElements = doc.select("th:containsOwn(Movie)");
        if (!movieElements.isEmpty()) {
            String movie = movieElements.first().parent().children().get(1).text();
            if (movie.contains("(")) {
                charDebut.setMovie(movie.substring(0, movie.indexOf(" (")).trim());
            } else {
                charDebut.setMovie(movie);
            }
        }

        //game
        Elements gameElements = doc.select("th:containsOwn(Game)");
        if (!gameElements.isEmpty()) {
            try {
                String game = gameElements.first().parent().children().get(1).text();
                if (game.contains("(")) {
                    charDebut.setGame(game.substring(0, game.indexOf(" (")).trim());
                } else {
                    charDebut.setGame(game);
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
        }

        //ova
        Elements ovaElements = doc.select("th:containsOwn(OVA)");
        if (!ovaElements.isEmpty()) {
            String ova = ovaElements.first().parent().children().get(1).text();
            if (ova.contains("(")) {
                charDebut.setOva(ova.substring(0, ova.indexOf(" (")).trim());
            } else {
                charDebut.setOva(ova);
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
            charDebut.setAppears(appears);
        }
        log.info("Debut info getted.");
        return charDebut;
    }
}
