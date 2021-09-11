package webscraping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webscraping.model.chapter.ChapterDoc;

@Repository
public interface ChapterRepository extends MongoRepository<ChapterDoc , Double> {

}
