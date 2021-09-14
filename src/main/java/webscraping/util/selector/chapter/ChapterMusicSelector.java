package webscraping.util.selector.chapter;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.chapter.ChapterMusic;

@Slf4j
public class ChapterMusicSelector {

    private ChapterMusicSelector(){
    }

    public static ChapterMusic getInfo(Document doc){
        ChapterMusic music = new ChapterMusic();

        try{
            Elements op = doc.select("div[data-source=\"opening song\"] div");
            music.setOpening(op.get(0).text().replace("\"", ""));

            Elements ed = doc.select("div[data-source=\"ending song\"] div");
            music.setEnding(ed.get(0).text().replace("\"", ""));
        }
        catch (IndexOutOfBoundsException e){
            log.error("Index : Music", e);
        }
        return music;
    }
}
