package webscraping.util;

import webscraping.model.village.VillageData;
import webscraping.model.village.VillageName;
import webscraping.model.village.VillageStatistic;

public class VillageInfoCheckNull {
    //checks to don't create empty object in json
    public static boolean checkNullInfoName(VillageName villageName) {
        return villageName.getEnglish() == null && villageName.getKanji() == null
                && villageName.getRomaji() == null;
    }

    public static boolean checkNullInfoData(VillageData villageData) {
        return villageData.getCountry() == null && villageData.getLeader() == null
                && villageData.getImgIcon() == null;
    }

    public static boolean checkNullStatistic(VillageStatistic villageStatistic) {
        return villageStatistic.getPopulation() == null && villageStatistic.getMilitary() == null
                && villageStatistic.getEconomy() == null;
    }
}
