package webscraping.selector.jutsu;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.jutsu.JutsuAnime;
import webscraping.model.jutsu.JutsuDebut;
import webscraping.model.jutsu.JutsuManga;

import java.util.ArrayList;
import java.util.List;

import static webscraping.util.JutsuInfoCheckNull.checkNullInfoAnime;
import static webscraping.util.JutsuInfoCheckNull.checkNullManga;

@Slf4j
public class JutsuDebutSelector {
    private JutsuDebutSelector() {
    }

    public static JutsuDebut getDebutJutsu(Document doc) {
        JutsuDebut jutsuDebut = new JutsuDebut();
        JutsuManga jutsuManga = new JutsuManga();
        JutsuAnime jutsuAnime = new JutsuAnime();

        //manga
        Elements mangaElements = doc.select("div h3:containsOwn(Manga)");
        if (!mangaElements.isEmpty()) {
            jutsuManga.setName(mangaElements.first().parent().children().select("a i").text().trim());

            String volume = mangaElements.first().parent().select("a").get(0).text().trim();
            jutsuManga.setVolume(Double.parseDouble(volume.substring(volume.indexOf("#") + 1)));

            String chapter = mangaElements.first().parent().select("a").get(1).text().trim();
            jutsuManga.setChapter(Double.parseDouble(chapter.substring(chapter.indexOf("#") + 1)));
        }
        jutsuDebut.setManga(checkNullManga(jutsuManga) ? null : jutsuManga);

        //anime
        Elements animeElements = doc.select("div[data-source=\"debut anime\"]");
        if (!animeElements.isEmpty()) {
            String animeText = animeElements.select("a").text().trim();
            jutsuAnime.setName(animeElements.select("a i").text().trim());
            jutsuAnime.setEpisode(Double.parseDouble(animeText.substring(animeText.indexOf("#") + 1)));
        }
        jutsuDebut.setAnime(checkNullInfoAnime(jutsuAnime) ? null : jutsuAnime);
        //novel
        Elements novelElements = doc.select("div[data-source=\"novel debut\"]");
        if (!novelElements.isEmpty()) {
            jutsuDebut.setNovel(novelElements.select("div i").text().trim());
        }

        //movie
        Elements movieElements = doc.select("div[data-source=\"movie debut\"]");
        if (!movieElements.isEmpty()) {
            jutsuDebut.setMovie(movieElements.select("div i").text().trim());
        }

        //game
        Elements gameElements = doc.select("div[data-source=\"game debut\"]");
        if (!gameElements.isEmpty()) {
            jutsuDebut.setGame(gameElements.select("div i").text().trim());
        }

        //ova
        Elements ovaElements = doc.select("div[data-source=\"ova debut\"]");
        if (!ovaElements.isEmpty()) {
            jutsuDebut.setOva(ovaElements.select("div a").text().trim());
        }

        //appears
        Elements appearsElements = doc.select("div h3:containsOwn(Appears in)");
        List<String> appearsList = new ArrayList<>();
        if (!appearsElements.isEmpty()) {
            String[] appearsElmtsSeparated = appearsElements.first().parent().children().select("div").text().split(", ");
            for (String appear : appearsElmtsSeparated) {
                appearsList.add(appear.trim());
            }
            jutsuDebut.setAppears(appearsList);
        }

        log.info("Jutsu debut info getted.");
        return jutsuDebut;
    }
}
