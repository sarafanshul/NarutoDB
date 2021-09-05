package webscraping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webscraping.document.CharacterDoc;

import java.util.List;

public interface CustomCharacterMongoRepository {

    /**
     * Finds the characters with ID in name
     *
     * @param name to match
     * @return List of characters found
     */
    List<CharacterDoc> getCharacterByMatchingEnglishName(String name);

    Page<CharacterDoc> findAllOrderByJutsusSizeDesc(Pageable pageable);
}
