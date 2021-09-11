package webscraping.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import webscraping.model.village.*;
import webscraping.repository.VillageRepository;
import webscraping.util.JsoupConnection;
import webscraping.util.selector.village.VillageDataSelector;
import webscraping.util.selector.village.VillageInfoSelector;
import webscraping.util.selector.village.VillageNameSelector;
import webscraping.util.selector.village.VillageStatisticSelector;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

                villageDoc.setId(name);
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

    public void insert(String id) {
        if (!getCheckVillageId(id).isPresent()) { //check if village already exists
            VillageDoc obj = getVillageInfo(id);
            if (obj.getName() != null && obj.getName().getEnglish() != null)
                villageRepository.insert(obj);
            else
                log.warn("village data incomplete");
        } else {
            log.warn("Village already exists.");
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Village already exists.");
        }
    }

    public Optional<VillageDoc> getCheckVillageId(String id) {
        return villageRepository.findById(id);
    }

    public VillageDoc getVillage(String id) {
        Optional<VillageDoc> obj = getCheckVillageId(id);
        if (!obj.isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Village not found.");
        }
        return obj.get();
    }

    public List<VillageDoc> getVillageByNameEnglishRegex(String name) {
        if (name.length() < 1) {
            log.warn("String length too short.");
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Length too short"
            );
        }
        return villageRepository.findByNameEnglishRegex(name);
    }

    public List<VillageDoc> getAllVillages() {
        return villageRepository.findAll();
    }

    public Page<VillageDoc> getAllVillagesPaged(Pageable pageable) {
        return villageRepository.findAll(pageable);
    }
}
