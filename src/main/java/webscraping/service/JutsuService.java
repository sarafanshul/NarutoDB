package webscraping.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import webscraping.document.JutsuDoc;
import webscraping.dto.JutsuDTO;
import webscraping.model.jutsu.JutsuDebut;
import webscraping.model.jutsu.JutsuInfo;
import webscraping.model.jutsu.JutsuName;
import webscraping.repository.JutsuRepository;
import webscraping.util.JsoupConnection;

import java.io.IOException;

import static webscraping.selector.jutsu.JutsuDebutSelector.getDebutJutsu;
import static webscraping.selector.jutsu.JutsuInfoSelector.getInfoJutsu;
import static webscraping.selector.jutsu.JutsuNameSelector.getNameJutsu;
import static webscraping.util.JutsuInfoCheckNull.checkNullDebut;
import static webscraping.util.JutsuInfoCheckNull.checkNullInfoName;

@Slf4j
@Service
public class JutsuService {

    @Autowired
    JutsuRepository jutsuRepository;

    public JutsuDoc getJutsuInfo(String jutsuName) {
        Document doc = JsoupConnection.connectionInfo(jutsuName);
        log.info("Jutsu url jsoup connected");
        JutsuDoc jutsuDoc = new JutsuDoc();

        if (doc != null) {
            try {
                JutsuName name = getNameJutsu(doc);
                JutsuInfo jutsuInfo = getInfoJutsu(doc);
                JutsuDebut jutsuDebut = getDebutJutsu(doc);

                jutsuDoc.setName(checkNullInfoName(name) ? null : name);
                jutsuDoc.setDescription(jutsuInfo.getDescription() == null ? null : jutsuInfo.getDescription());
                jutsuDoc.setImage(jutsuInfo.getImage() == null ? null : jutsuInfo.getImage());

                jutsuDoc.setDebut(checkNullDebut(jutsuDebut) ? null : jutsuDebut);
                jutsuDoc.setClassification(jutsuInfo.getClassification() == null ? null : jutsuInfo.getClassification());
                jutsuDoc.setNature(jutsuInfo.getNature() == null ? null : jutsuInfo.getNature());
                jutsuDoc.setRank(jutsuInfo.getRank() == null ? null : jutsuInfo.getRank());
                jutsuDoc.setType(jutsuInfo.getType() == null ? null : jutsuInfo.getType());
                jutsuDoc.setRange(jutsuInfo.getRange() == null ? null : jutsuInfo.getRange());
                jutsuDoc.setHandSeals(jutsuInfo.getHandSeals() == null ? null : jutsuInfo.getHandSeals());
                jutsuDoc.setUsers(jutsuInfo.getUsers() == null ? null : jutsuInfo.getUsers());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.warn("Document is empty.");
        }

        return jutsuDoc;
    }

    public void insert(JutsuDoc jutsuDoc) {
        if (getCheckJutsu(jutsuDoc.getName().getEnglish()) == null) { //check if jutsu already exists
            jutsuRepository.insert(jutsuDoc);
        } else {
            log.warn("Jutsu already exists.");
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Jutsu already exists.");
        }
    }

    public JutsuDTO getCheckJutsu(String name) {
        return jutsuRepository.findByNameEnglish(name);
    }

    public JutsuDTO getJutsu(String name) {
        if (jutsuRepository.findByNameEnglish(name) == null) {
            log.warn("Jutsu not found.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Jutsu not found.");
        }
        return jutsuRepository.findByNameEnglish(name);
    }
}
