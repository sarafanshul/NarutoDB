package webscraping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import webscraping.model.jutsu.JutsuDTO;
import webscraping.model.jutsu.JutsuDoc;

import java.util.List;

@Repository
public interface JutsuRepository extends MongoRepository<JutsuDoc, String> {

    @Deprecated
    JutsuDTO findByNameEnglish(String name);

    /**
     * Finds the jutsus with English name in name using Regex Match , offers a partial text
     * search due to wildcard matching
     * <br>
     * <b>No Indexing is required hence can be slow compared to 'text search' offered by mongo</b>
     *
     * @param name to match
     * @return List of jutsus found
     */
    @Query("{ 'name.english' : { '$regex' : '?0' , '$options' : 'i' } }")
    List<JutsuDoc> findByNameEnglishRegex(String name);

    /**
     * Finds the jutsus with English name in name using Regex Match , offers a partial text
     * search due to wildcard matching
     * <br>
     * <b>No Indexing is required hence can be slow compared to 'text search' offered by mongo</b>
     *
     * @param name to match
     * @return Page of jutsus found
     */
    @Query("{ 'name.english' : { '$regex' : '?0' , '$options' : 'i' } }")
    Page<JutsuDoc> findByNameEnglishRegexPaged(String name, Pageable pageable);
}
