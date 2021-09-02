package webscraping.selector.tool;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.tool.ToolAnime;
import webscraping.model.tool.ToolDebut;
import webscraping.model.tool.ToolManga;

import java.util.ArrayList;
import java.util.List;

import static webscraping.util.ToolInfoCheckNull.checkNullInfoAnime;
import static webscraping.util.ToolInfoCheckNull.checkNullManga;

@Slf4j
public class ToolDebutSelector {
    private ToolDebutSelector() {
    }

    public static ToolDebut getToolDebut(Document doc) {
        ToolDebut toolDebut = new ToolDebut();
        ToolManga toolManga = new ToolManga();
        ToolAnime toolAnime = new ToolAnime();

        //manga
        Elements mangaElements = doc.select("div h3:containsOwn(Manga)");
        if (!mangaElements.isEmpty()) {
            toolManga.setName(mangaElements.first().parent().children().select("a i").text().trim());

            String volume = mangaElements.first().parent().select("a").get(0).text().trim();
            toolManga.setVolume(Double.parseDouble(volume.substring(volume.indexOf("#") + 1)));

            String chapter = mangaElements.first().parent().select("a").get(1).text().trim();
            toolManga.setChapter(Double.parseDouble(chapter.substring(chapter.indexOf("#") + 1)));
        }
        toolDebut.setManga(checkNullManga(toolManga) ? null : toolManga);

        //anime
        Elements animeElements = doc.select("div[data-source=\"debut anime\"]");
        if (!animeElements.isEmpty()) {
            String animeText = animeElements.select("a").text().trim();
            toolAnime.setName(animeElements.select("a i").text().trim());
            toolAnime.setEpisode(Double.parseDouble(animeText.substring(animeText.indexOf("#") + 1)));
        }
        toolDebut.setAnime(checkNullInfoAnime(toolAnime) ? null : toolAnime);

        //novel
        Elements novelElements = doc.select("div[data-source=\"novel debut\"]");
        if (!novelElements.isEmpty()) {
            toolDebut.setNovel(novelElements.select("div i").text().trim());
        }

        //movie
        Elements movieElements = doc.select("div[data-source=\"movie debut\"]");
        if (!movieElements.isEmpty()) {
            toolDebut.setMovie(movieElements.select("div i").text().trim());
        }

        //game
        Elements gameElements = doc.select("div[data-source=\"game debut\"]");
        if (!gameElements.isEmpty()) {
            toolDebut.setGame(gameElements.select("div i").text().trim());
        }

        //ova
        Elements ovaElements = doc.select("div[data-source=\"ova debut\"]");
        if (!ovaElements.isEmpty()) {
            toolDebut.setOva(ovaElements.select("div a").text().trim());
        }

        //appears
        Elements appearsElements = doc.select("div h3:containsOwn(Appears in)");
        List<String> appearsList = new ArrayList<>();
        if (!appearsElements.isEmpty()) {
            String[] appearsElmtsSeparated = appearsElements.first().parent().children().select("div").text().split(", ");
            for (String appear : appearsElmtsSeparated) {
                appearsList.add(appear.trim());
            }
            toolDebut.setAppears(appearsList);
        }

        log.info("Tool debut info getted.");
        return toolDebut;
    }
}
