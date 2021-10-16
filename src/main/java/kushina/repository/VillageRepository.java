/*
 * @author Anshul Saraf
 */

package kushina.repository;

import kushina.model.village.VillageDTO;
import kushina.model.village.VillageDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageRepository extends MongoRepository<VillageDoc, String> {

    @Deprecated
    VillageDTO findByNameEnglish(String name);

    @Query("{'name.english' : { '$regex':'?0' , '$options':'i' } }")
    List<VillageDoc> findByNameEnglishRegex(String name);
}
