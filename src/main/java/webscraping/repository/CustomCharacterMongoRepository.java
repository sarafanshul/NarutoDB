package webscraping.repository;

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
}
