package webscraping.util;

import webscraping.model.jutsu.JutsuAnime;
import webscraping.model.jutsu.JutsuDebut;
import webscraping.model.jutsu.JutsuManga;
import webscraping.model.jutsu.JutsuName;

public class JutsuInfoCheckNull {
    //checks to don't create empty object in json
    public static boolean checkNullInfoName(JutsuName jutsuName) {
        return jutsuName.getEnglish() == null && jutsuName.getKanji() == null
                && jutsuName.getRomaji() == null && jutsuName.getOther() == null;
    }

    public static boolean checkNullInfoAnime(JutsuAnime jutsuAnime) {
        return jutsuAnime.getName() == null && jutsuAnime.getEpisode() == null;
    }

    public static boolean checkNullManga(JutsuManga jutsuManga) {
        return jutsuManga.getName() == null && jutsuManga.getVolume() == null
                && jutsuManga.getChapter() == null;
    }

    public static boolean checkNullDebut(JutsuDebut jutsuDebut) {
        return jutsuDebut.getManga() == null && jutsuDebut.getAnime() == null
                && jutsuDebut.getNovel() == null && jutsuDebut.getMovie() == null
                && jutsuDebut.getGame() == null && jutsuDebut.getOva() == null;
    }
}
