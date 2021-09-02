package webscraping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webscraping.document.JutsuDoc;
import webscraping.dto.JutsuDTO;

@Repository
public interface JutsuRepository extends MongoRepository<JutsuDoc, String> {
    JutsuDTO findByNameEnglish(String name);
}
