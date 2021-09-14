/*
 * @author Anshul Saraf
 */

package kushina.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import kushina.model.team.TeamDTO;
import kushina.model.team.TeamDoc;

@Repository
public interface TeamRepository extends MongoRepository<TeamDoc, String> {
    TeamDTO findByNameEnglish(String name);
}
