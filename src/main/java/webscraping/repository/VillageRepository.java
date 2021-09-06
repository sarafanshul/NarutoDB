package webscraping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webscraping.model.village.VillageDoc;
import webscraping.model.village.VillageDTO;

@Repository
public interface VillageRepository extends MongoRepository<VillageDoc, String> {
    VillageDTO findByNameEnglish(String name);
}
