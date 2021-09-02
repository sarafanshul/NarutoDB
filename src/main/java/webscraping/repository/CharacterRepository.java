package webscraping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webscraping.document.CharacterDoc;
import webscraping.dto.CharacterDTO;

@Repository
public interface CharacterRepository extends MongoRepository<CharacterDoc, String> {

    CharacterDTO findByNameEnglish(String name);

}
