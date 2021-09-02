package webscraping.util;

import webscraping.model.character.*;

import java.util.List;

public class CharacterInfoCheckNull {
    //checks to don't create empty object in json
    public static boolean checkNullInfoRank(CharRank charRank) {
        return charRank.getNinjaRank() == null && charRank.getNinjaRegistration() == null
                && charRank.getAcademyGradAge() == null && charRank.getChuninPromAge() == null;
    }

    public static boolean checkNullInfoManga(CharManga charManga) {
        return charManga.getChapter() == null && charManga.getVolume() == null;
    }

    public static boolean checkNullInfoAnime(CharAnime charAnime) {
        return charAnime.getName() == null && charAnime.getEpisode() == null;
    }

    public static boolean checkNullInfoVoice(CharVoice charVoice) {
        return charVoice.getEnglish() == null && charVoice.getJapanese() == null;
    }

    public static boolean checkNullInfoDebut(CharDebut charDebut) {
        return charDebut.getManga() == null && charDebut.getAnime() == null
                && charDebut.getNovel() == null && charDebut.getMovie() == null
                && charDebut.getGame() == null && charDebut.getOva() == null;
    }

    public static boolean checkNullInfoPersonal(CharPersonal charPersonal) {
        return charPersonal.getBirthDate() == null && charPersonal.getSex() == null
                && charPersonal.getAge() == null && charPersonal.getHeight() == null
                && charPersonal.getWeight() == null && charPersonal.getBloodType() == null;
    }

    public static boolean checkNullDatabook(List<CharDatabook> charDatabooks) {
        return charDatabooks.isEmpty();
    }

}
