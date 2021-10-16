/*
 * @author Anshul Saraf
 */

package kushina.repository;

import kushina.model.team.TeamDTO;
import kushina.model.team.TeamDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<TeamDoc, String> {
    TeamDTO findByNameEnglish(String name);
}
