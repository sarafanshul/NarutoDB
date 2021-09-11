package webscraping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webscraping.model.tool.ToolDTO;
import webscraping.model.tool.ToolDoc;

@Repository
public interface ToolRepository extends MongoRepository<ToolDoc, String> {
    ToolDTO findByNameEnglish(String name);
}
