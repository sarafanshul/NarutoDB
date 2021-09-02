package webscraping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webscraping.document.ToolDoc;
import webscraping.dto.ToolDTO;

@Repository
public interface ToolRepository extends MongoRepository<ToolDoc, String> {
    ToolDTO findByNameEnglish(String name);
}
