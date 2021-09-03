package webscraping.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.TextCriteria;
import webscraping.document.CharacterDoc;

import java.util.List;


public class CustomCharacterMongoRepositoryImpl implements CustomCharacterMongoRepository{

    public final String ENGLISH_NAME = "name.english";
    public final String COLLECTION_NAME = "characters";

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Finds the characters with ID in name
     *
     * Usage - Right now it only returns the matching word ie -> Naturo Uzumaki can be found by
     * searching - uzumaki (not case sensetive) , but not - uzu , ie a full word is needed
     *
     * Text Indexing is used for matching
     *
     * @param name to match
     * @return List of characters found
     */
    @Override
    public List<CharacterDoc> getCharacterByMatchingEnglishName(String name) {
        Aggregation pipeline = Aggregation.newAggregation(
                CharacterDoc.class,
                Aggregation.match(
                        TextCriteria
                                .forDefaultLanguage()
                                .matchingAny(name)
                                .caseSensitive(false)
                ),
                Aggregation.sort(Sort.Direction.ASC , ENGLISH_NAME)
        );
        return mongoTemplate.aggregate(pipeline , COLLECTION_NAME , CharacterDoc.class).getMappedResults();
    }
}
