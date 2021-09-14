package webscraping.util.selector.chapter;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.chapter.ChapterManga;

@Slf4j
public class ChapterMangaSelector {

    private ChapterMangaSelector(){}

    public static ChapterManga getInfo(Document doc){

        ChapterManga chapterManga = new ChapterManga();
        try{
            Elements elem = doc.select("div[data-source=\"chapters\"] div");
            chapterManga.setChapters( elem.eachText() );
        }catch (Exception e){
            log.error("Unknown Exception , Chapter manga" , e);
        }

        return chapterManga ;
    }

}
