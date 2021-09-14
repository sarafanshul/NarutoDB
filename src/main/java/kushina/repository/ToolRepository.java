/*
 * @author Anshul Saraf
 */

package kushina.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import kushina.model.tool.ToolDTO;
import kushina.model.tool.ToolDoc;

@Repository
public interface ToolRepository extends MongoRepository<ToolDoc, String> {
    ToolDTO findByNameEnglish(String name);
}
