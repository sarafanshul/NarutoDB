package webscraping.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import webscraping.model.jutsu.JutsuDoc;
import webscraping.model.jutsu.JutsuDebut;
import webscraping.model.jutsu.JutsuInfo;
import webscraping.model.jutsu.JutsuName;
import webscraping.repository.JutsuRepository;
import webscraping.util.JsoupConnection;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static webscraping.util.selector.jutsu.JutsuDebutSelector.getDebutJutsu;
import static webscraping.util.selector.jutsu.JutsuInfoSelector.getInfoJutsu;
import static webscraping.util.selector.jutsu.JutsuNameSelector.getNameJutsu;
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

                jutsuDoc.setId(jutsuName);
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

    public void insert(String name) {
        if (!getCheckJutsuId(name).isPresent()) { //check if jutsu already exists
            jutsuRepository.insert(getJutsuInfo(name));
        } else {
            log.warn("Jutsu already exists.");
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Jutsu already exists.");
        }
    }

    private Optional<JutsuDoc> getCheckJutsuId(String id) {
        return jutsuRepository.findById(id);
    }

    public JutsuDoc getJutsu(String name) {
        Optional<JutsuDoc> obj = getCheckJutsuId(name);
        if (!obj.isPresent()) {
            log.warn("Jutsu not found.");
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Jutsu not found.");
        }
        return obj.get();
    }

    public List<JutsuDoc> getJutsuByNameEnglishRegex(String name) {
        if (name.length() < 1) {
            log.warn("String length too short.");
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Length too short"
            );
        }
        return jutsuRepository.findByNameEnglishRegex(name);
    }


    public List<JutsuDoc> getAllJutsus() {
        return jutsuRepository.findAll();
    }

    public Page<JutsuDoc> getAllJutsusPaged(Pageable pageable) {
        return jutsuRepository.findAll(pageable);
    }

    public Page<JutsuDoc> getJutsuByNameEnglishRegexPaged(String name, Pageable pageable) {
        if (name.length() < 1) {
            log.warn("String length too short.");
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Length too short"
            );
        }
        return jutsuRepository.findByNameEnglishRegexPaged(name , pageable);
    }
}
