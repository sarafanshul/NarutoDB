package webscraping.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import webscraping.model.village.VillageDoc;
import webscraping.model.village.VillageDTO;
import webscraping.model.village.VillageData;
import webscraping.model.village.VillageInfo;
import webscraping.model.village.VillageName;
import webscraping.model.village.VillageStatistic;
import webscraping.repository.VillageRepository;
import webscraping.util.selector.village.VillageDataSelector;
import webscraping.util.selector.village.VillageInfoSelector;
import webscraping.util.selector.village.VillageNameSelector;
import webscraping.util.selector.village.VillageStatisticSelector;
import webscraping.util.JsoupConnection;

import java.io.IOException;

import static webscraping.util.VillageInfoCheckNull.*;

@Slf4j
@Service
public class VillageService {

    @Autowired
    VillageRepository villageRepository;

    public VillageDoc getVillageInfo(String name) {
        Document doc = JsoupConnection.connectionInfo(name);
        log.info("Village url jsoup connected");
        VillageDoc villageDoc = new VillageDoc();

        if (doc != null) {
            try {
                VillageName villageName = VillageNameSelector.getVillageName(doc);
                VillageData villageData = VillageDataSelector.getVillageData(doc);
                VillageInfo villageInfo = VillageInfoSelector.getInfoVillage(doc);
                VillageStatistic villageStatistic = VillageStatisticSelector.getVillageStatistic(doc);

                villageDoc.setName(checkNullInfoName(villageName) ? null : villageName);
                villageDoc.setDescription(villageInfo.getDescription() == null ? null : villageInfo.getDescription());
                villageDoc.setImage(villageInfo.getImage() == null ? null : villageInfo.getImage());
                villageDoc.setData(checkNullInfoData(villageData) ? null : villageData);
                villageDoc.setStatistic(checkNullStatistic(villageStatistic) ? null : villageStatistic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.warn("Document is empty.");
        }
        return villageDoc;
    }

    public void insert(VillageDoc villageDoc) {
        if (getCheckVillage(villageDoc.getName().getEnglish()) == null) { //check if village already exists
            villageRepository.insert(villageDoc);
        } else {
            log.warn("Village already exists.");
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Village already exists.");
        }
    }

    public VillageDTO getCheckVillage(String name) {
        return villageRepository.findByNameEnglish(name);
    }

    public VillageDTO getVillage(String name) {
        if (villageRepository.findByNameEnglish(name) == null) {
            log.warn("Village not found.");
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Village not found.");
        }
        return villageRepository.findByNameEnglish(name);
    }
}
