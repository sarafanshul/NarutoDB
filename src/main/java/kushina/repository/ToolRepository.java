/*
 * @author Anshul Saraf
 */

package kushina.repository;

import kushina.model.tool.ToolDTO;
import kushina.model.tool.ToolDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends MongoRepository<ToolDoc, String> {
    ToolDTO findByNameEnglish(String name);
}
