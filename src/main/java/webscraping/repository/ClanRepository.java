package webscraping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webscraping.document.ClanDoc;
import webscraping.dto.ClanDTO;

@Repository
public interface ClanRepository extends MongoRepository<ClanDoc, String> {
    ClanDTO findByNameEnglish(String name);
}
