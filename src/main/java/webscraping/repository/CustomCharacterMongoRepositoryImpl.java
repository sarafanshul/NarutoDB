package webscraping.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.TextCriteria;
import webscraping.model.character.CharacterDoc;

import java.util.Arrays;
import java.util.List;


public class CustomCharacterMongoRepositoryImpl implements CustomCharacterMongoRepository {

    public final String ENGLISH_NAME = "name.english";
    public final String COLLECTION_NAME = "characters";

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Finds the characters with ID in name
     * <p>
     * Usage - Right now it only returns the matching word ie -> Naturo Uzumaki can be found by
     * searching - uzumaki (not case sensetive) , but not - uzu , ie a full word is needed
     * <p>
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
            Aggregation.sort(Sort.Direction.ASC, ENGLISH_NAME)
        );
        return mongoTemplate.aggregate(pipeline, COLLECTION_NAME, CharacterDoc.class).getMappedResults();
    }

    /**
     * Returns a Paged Result of Characters W.R.T Characters::jutsus::length , order DESC
     * @param pageable Pageable object for pagination
     * @return paged result in order of Jutsu size , DESC
     */
    /*
    while adding a new field in project all others get excluded , hence brute force
    https://stackoverflow.com/questions/19431773/include-all-existing-fields-and-add-new-fields-to-document
     */
    @Override
    public Page<CharacterDoc> findAllOrderByJutsusSizeDesc(Pageable pageable) {
        Aggregation pipeline = Aggregation.newAggregation(
            p ->
                new Document("$project",
                    new Document("jutsus_count",
                        new Document("$size",
                            new Document("$ifNull", Arrays.asList("$jutsus", Arrays.asList()))))
                        .append("_id", 1L)
                        .append("name", 1L)
                        .append("description", 1L)
                        .append("images", 1L)
                        .append("debut", 1L)
                        .append("voices", 1L)
                        .append("personal", 1L)
                        .append("charRank", 1L)
                        .append("family", 1L)
                        .append("natureTypes", 1L)
                        .append("uniqueTraits", 1L)
                        .append("jutsus", 1L)
                        .append("tools", 1L)
                        .append("databooks", 1L)),
            q ->
                new Document("$sort",
                    new Document("jutsus_count", -1L)
                ),
            Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize()),
            Aggregation.limit(pageable.getPageSize())
        );
        List<CharacterDoc> results = mongoTemplate.aggregate(pipeline, COLLECTION_NAME, CharacterDoc.class).getMappedResults();
        return new PageImpl<>(results, pageable, results.size());
    }
}
