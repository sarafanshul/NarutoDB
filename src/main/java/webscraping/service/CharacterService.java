package webscraping.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import webscraping.document.CharacterDoc;
import webscraping.model.character.*;
import webscraping.repository.CharacterRepository;
import webscraping.util.JsoupConnection;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static webscraping.selector.character.CharDatabookSelector.getDatabookInfo;
import static webscraping.selector.character.CharDebutSelector.getInfoDebut;
import static webscraping.selector.character.CharInfoSelector.getInfoBase;
import static webscraping.selector.character.CharNameSelector.getInfoName;
import static webscraping.selector.character.CharPersonalSelector.getInfoPersonal;
import static webscraping.selector.character.CharRankSelector.getInfoRank;
import static webscraping.selector.character.CharVoiceSelector.getInfoVoices;
import static webscraping.util.CharacterInfoCheckNull.*;

@Service
@Slf4j
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    private CharacterDoc getCharacterInfo(String charName) {
        Document doc = JsoupConnection.connectionInfo(charName);
        log.info("Character url jsoup connected for {} ", charName);
        CharacterDoc characterDoc = new CharacterDoc();

        if (doc != null) {
            try {
                CharName name = getInfoName(doc);
                CharInfo charInfo = getInfoBase(doc);
                CharDebut charDebut = getInfoDebut(doc);
                CharPersonal charPersonal = getInfoPersonal(doc);
                CharRank charRank = getInfoRank(doc);
                CharVoice charVoice = getInfoVoices(doc);
                List<CharDatabook> charDatabooks = getDatabookInfo(doc);

                characterDoc.setId(charName); // link to character
                characterDoc.setName(name);
                characterDoc.setDescription(charInfo.getDescription() == null ? null : charInfo.getDescription());
                characterDoc.setImages(charInfo.getImages() == null ? null : charInfo.getImages());
                characterDoc.setDebut(checkNullInfoDebut(charDebut) ? null : charDebut);
                characterDoc.setVoices(checkNullInfoVoice(charVoice) ? null : charVoice);
                characterDoc.setPersonal(checkNullInfoPersonal(charPersonal) ? null : charPersonal);
                characterDoc.setCharRank(checkNullInfoRank(charRank) ? null : charRank);
                characterDoc.setFamily(charInfo.getFamily() == null ? null : charInfo.getFamily());
                characterDoc.setNatureTypes(charInfo.getNatureTypes() == null ? null : charInfo.getNatureTypes());
                characterDoc.setUniqueTraits(charInfo.getUniqueTraits() == null ? null : charInfo.getUniqueTraits());
                characterDoc.setJutsus(charInfo.getJutsus() == null ? null : charInfo.getJutsus());
                characterDoc.setTools(charInfo.getTools() == null ? null : charInfo.getTools());
                characterDoc.setDatabooks(checkNullDatabook(charDatabooks) ? null : charDatabooks);
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        } else {
            log.warn("Document is empty.");
        }
        return characterDoc;
    }

    public CharacterDoc insert(String name) {
        if (!getCheckCharacterId(name).isPresent()) { //check if character already
            // not exists
            log.warn("{} , Character does not exists.", name);
            return characterRepository.insert(getCharacterInfo(name));
        } else {
            log.warn("Character already exists.");
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Character already exists.");
        }
    }

    private Optional<CharacterDoc> getCheckCharacterId(String name) {
        return characterRepository.findById(name);
    }

    public CharacterDoc getCharacter(String name) {
        Optional<CharacterDoc> obj = characterRepository.findById(name);
        if (!obj.isPresent()) {
            log.warn("Character not found.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Character not found.");
        }
        return obj.get();
    }

    public List<CharacterDoc> getCharactersByName(String name) {
        if( name.length() <= 1 ){
            log.warn("String length too short.");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST , "Length too short"
            ) ;
        }
        return characterRepository.getCharacterByMatchingEnglishName(name);
    }

    public  List<CharacterDoc> getCharacterByNameEnglishRegex(String name){
        if( name.length() < 1 ){
            log.warn("String length too short.");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST , "Length too short"
            ) ;
        }
        return characterRepository.findByNameEnglishRegex(name);
    }

    public List<CharacterDoc> getAllCharacters(){
        return characterRepository.findAll();
    }
}
