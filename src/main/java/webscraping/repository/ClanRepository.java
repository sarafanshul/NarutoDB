package webscraping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import webscraping.document.CharacterDoc;
import webscraping.document.ClanDoc;
import webscraping.dto.ClanDTO;

import java.util.List;

@Repository
public interface ClanRepository extends MongoRepository<ClanDoc, String>  {
    List<ClanDoc> findByNameEnglish(String name);

    /**
     * Finds the clans with ID in name using Regex Match , not indexed like above one ,
     * hence can be slow but offers a partial text search due to wildcard matching
     *
     * @param name to match
     * @return List of clans found
     */
    @Query("{'name.english' : { '$regex':'?0' , '$options':'i' } }")
    List<ClanDoc> findByNameEnglishRegex(String name);
}
