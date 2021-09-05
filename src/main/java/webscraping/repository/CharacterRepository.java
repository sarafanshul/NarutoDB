package webscraping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import webscraping.document.CharacterDoc;
import webscraping.dto.CharacterDTO;

import java.util.List;

@Repository
public interface CharacterRepository extends MongoRepository<CharacterDoc, String>, CustomCharacterMongoRepository {

    @Deprecated
    CharacterDTO findByNameEnglish(String name);

    /**
     * Finds the characters with ID in name using Regex Match , not indexed like above one ,
     * hence can be slow but offers a partial text search due to wildcard matching
     *
     * @param name to match
     * @return List of characters found
     */
    @Query("{'name.english' : { '$regex':'?0' , '$options':'i' } }")
    List<CharacterDoc> findByNameEnglishRegex(String name);

}
