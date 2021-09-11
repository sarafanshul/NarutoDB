package webscraping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webscraping.model.team.TeamDTO;
import webscraping.model.team.TeamDoc;

@Repository
public interface TeamRepository extends MongoRepository<TeamDoc, String> {
    TeamDTO findByNameEnglish(String name);
}
