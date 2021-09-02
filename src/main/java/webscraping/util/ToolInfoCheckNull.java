package webscraping.util;

import webscraping.model.tool.ToolAnime;
import webscraping.model.tool.ToolDebut;
import webscraping.model.tool.ToolManga;
import webscraping.model.tool.ToolName;

public class ToolInfoCheckNull {
    //checks to don't create empty object in json
    public static boolean checkNullInfoName(ToolName toolName) {
        return toolName.getEnglish() == null && toolName.getKanji() == null
                && toolName.getRomaji() == null && toolName.getOther() == null;
    }

    public static boolean checkNullInfoAnime(ToolAnime toolAnime) {
        return toolAnime.getName() == null && toolAnime.getEpisode() == null;
    }

    public static boolean checkNullManga(ToolManga toolManga) {
        return toolManga.getName() == null && toolManga.getVolume() == null
                && toolManga.getChapter() == null;
    }

    public static boolean checkNullDebut(ToolDebut toolDebut) {
        return toolDebut.getManga() == null && toolDebut.getAnime() == null
                && toolDebut.getNovel() == null && toolDebut.getMovie() == null
                && toolDebut.getGame() == null && toolDebut.getOva() == null;
    }
}
