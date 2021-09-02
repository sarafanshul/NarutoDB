package webscraping.util;

import webscraping.model.team.*;

public class TeamInfoCheckNull {
    //checks to don't create empty object in json
    public static boolean checkNullInfoName(TeamName teamName) {
        return teamName.getEnglish() == null && teamName.getKanji() == null
                && teamName.getOthers() == null;
    }

    public static boolean checkNullInfoAnime(TeamAnime teamAnime) {
        return teamAnime.getName() == null && teamAnime.getEpisode() == null;
    }

    public static boolean checkNullManga(TeamManga teamManga) {
        return teamManga.getName() == null && teamManga.getVolume() == null
                && teamManga.getChapter() == null;
    }

    public static boolean checkNullDebut(TeamDebut teamDebut) {
        return teamDebut.getManga() == null && teamDebut.getAnime() == null
                && teamDebut.getNovel() == null && teamDebut.getMovie() == null
                && teamDebut.getGame() == null && teamDebut.getOva() == null;
    }

    public static boolean checkNullInfo(TeamInfo teamInfo) {
        return teamInfo.getLeaders() == null && teamInfo.getMembers() == null
                && teamInfo.getAffiliations() == null;
    }
}
