package webscraping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import webscraping.model.clan.ClanDTO;
import webscraping.model.clan.ClanDoc;

import java.util.List;

@Repository
public interface ClanRepository extends MongoRepository<ClanDoc, String>, CustomClanMongoRepository {

    @Deprecated
    List<ClanDTO> findByNameEnglish(String name);

    /**
     * Finds the clans with ID in name using Regex Match , offers a partial text
     * search due to wildcard matching, no
     * <br>
     * <b>No Indexing is required hence can be slow compared to 'text search' offered by mongo</b>
     *
     * @param name to match
     * @return List of clans found
     */
    @Query("{'name.english' : { '$regex':'?0' , '$options':'i' } }")
    List<ClanDoc> findByNameEnglishRegex(String name);
}
