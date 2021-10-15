/*
 * @author Anshul Saraf
 */

package kushina.service;

import kushina.model.chapter.ChapterDoc;
import kushina.model.character.*;
import kushina.model.jutsu.JutsuDoc;
import kushina.repository.ChapterRepository;
import kushina.repository.CharacterRepository;
import kushina.repository.JutsuRepository;
import kushina.util.JsoupConnection;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static kushina.util.CharacterInfoCheckNull.*;
import static kushina.util.selector.character.CharDatabookSelector.getDatabookInfo;
import static kushina.util.selector.character.CharDebutSelector.getInfoDebut;
import static kushina.util.selector.character.CharInfoSelector.getInfoBase;
import static kushina.util.selector.character.CharNameSelector.getInfoName;
import static kushina.util.selector.character.CharPersonalSelector.getInfoPersonal;
import static kushina.util.selector.character.CharRankSelector.getInfoRank;
import static kushina.util.selector.character.CharVoiceSelector.getInfoVoices;

@Service
@Slf4j
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    JutsuRepository jutsuRepository;

    @Autowired
    ChapterRepository chapterRepository;

    private CharacterDoc getCharacterInfo(String charName) {
        Document doc = JsoupConnection.connectionInfo(charName);
        log.info("Character url jsoup connected for {} ", charName);
        CharacterDoc characterDoc = new CharacterDoc();

        if (doc != null) {
            try {
                CharName name = getInfoName(doc);
                if (name.getEnglish() == null || name.getEnglish().length() == 0)
                    name.setEnglish(charName);
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
        if (name.length() <= 1) {
            log.warn("String length too short.");
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Length too short"
            );
        }
        return characterRepository.getCharacterByMatchingEnglishName(name);
    }

    public List<CharacterDoc> getCharacterByNameEnglishRegex(String name) {
        if (name.length() < 1) {
            log.warn("String length too short.");
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Length too short"
            );
        }
        return characterRepository.findByNameEnglishRegex(name);
    }

    public List<CharacterDoc> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Page<CharacterDoc> getAllCharactersPaged(Pageable pageable) {
        return characterRepository.findAll(pageable);
    }

    public Page<CharacterDoc> findAllOrderByJutsusSizeDesc(Pageable pageable) {
        return characterRepository.findAllOrderByJutsusSizeDesc(pageable);
    }

    public Page<CharacterDoc> findAllOrderByJutsusSizeAsc(Pageable pageable) {
        return characterRepository.findAllOrderByJutsusSizeAsc(pageable);
    }

    public Page<CharacterDoc> getCharacterByNameEnglishRegexPaged(String name, Pageable pageable) {
        if (name.length() < 1) {
            log.warn("String length too short.");
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Length too short"
            );
        }
        return characterRepository.findByNameEnglishRegexPaged(name, pageable);
    }

    public Page<CharacterDoc> findAllFilterByNameEnglishCoreNaruto(Pageable pageable) {
        return characterRepository.findAllFilterByNameEnglishCoreNaruto(pageable);
    }

    public List<JutsuDoc> getCharacterJutsusFiltered(String id) {
        Optional<CharacterDoc> character = getCheckCharacterId(id);
        if (!character.isPresent()) {
            log.warn("Character not found.");
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Character not found.");
        }
        return character.get().getJutsus().stream().map(jutsuName -> {
            String jutsuId = jutsuName.replace(" ", "_"); // id's are first name split by '_'
            List<JutsuDoc> result = jutsuRepository.findJutsuByIdFiltered(jutsuId);
            if (!result.isEmpty())
                return result.get(0);
            else
                return null;
        })
            .filter(
                Objects::nonNull
            )
            .collect(Collectors.toList());
    }

    public List<ChapterDoc> getCharacterDebutChapter(String id) {
        Optional<CharacterDoc> character = getCheckCharacterId(id);
        if (!character.isPresent()) {
            log.warn("character not found");
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Character not found."
            );
        }
        CharAnime anime = character.get().getDebut().getAnime();
        if (anime.getName() == null || anime.getEpisode() == null)
            throw new ResponseStatusException(
                HttpStatus.NO_CONTENT, "No anime associated with character!"
            );
        return chapterRepository.findChapterBySeriesAndEpisode(
            anime.getName().replace("Naruto Shippūden", "Naruto: Shippūden"),
            anime.getEpisode()
        );
    }
}
