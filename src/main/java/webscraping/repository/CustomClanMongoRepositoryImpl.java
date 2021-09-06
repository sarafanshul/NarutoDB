package webscraping.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import webscraping.model.clan.ClanDoc;

import java.util.Arrays;
import java.util.List;

public class CustomClanMongoRepositoryImpl implements CustomClanMongoRepository{

    public final String COLLECTION_NAME = "clans";

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Finds all Clans W.R.T their Clan::member::length
     * @param pageable a Pageable object
     * @return Paged collection
     */
    @Override
    public Page<ClanDoc> getClansOrderedByMemberSize(Pageable pageable) {
        Aggregation pipeline = Aggregation.newAggregation(
            p -> new Document("$project",
                new Document("members_size",
                    new Document("$size",
                        new Document("$ifNull", Arrays.asList("$members", Arrays.asList()))))
                    .append("_id", 1L)
                    .append("name", 1L)
                    .append("description", 1L)
                    .append("image", 1L)
                    .append("appears", 1L)
                    .append("affiliation", 1L)
                    .append("kekkeiGenkai", 1L)
                    .append("members", 1L)
                    .append("jutsus", 1L)
            ) ,
            q -> new Document("$sort",
                new Document("members_size", -1L)
            ),
            Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize()),
            Aggregation.limit(pageable.getPageSize())
        );
        List<ClanDoc> result = mongoTemplate.aggregate(pipeline , COLLECTION_NAME ,ClanDoc.class).getMappedResults();

        return new PageImpl<>(result , pageable , result.size());
    }

    /**
     * Finds all Clans W.R.T their Clan::jutsus::length
     * @param pageable a Pageable object
     * @return Paged collection
     */
    @Override
    public Page<ClanDoc> getClansOrderedByJutsusSize(Pageable pageable) {
        Aggregation pipeline = Aggregation.newAggregation(
            p -> new Document("$project",
                new Document("jutsus_size",
                    new Document("$size",
                        new Document("$ifNull", Arrays.asList("$jutsus", Arrays.asList()))))
                    .append("_id", 1L)
                    .append("name", 1L)
                    .append("description", 1L)
                    .append("image", 1L)
                    .append("appears", 1L)
                    .append("affiliation", 1L)
                    .append("kekkeiGenkai", 1L)
                    .append("members", 1L)
                    .append("jutsus", 1L)
            ),
            q -> new Document("$sort",
                new Document("jutsus_size", -1L)
            ),
            Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize()),
            Aggregation.limit(pageable.getPageSize())
        );
        List<ClanDoc> result = mongoTemplate.aggregate(pipeline , COLLECTION_NAME ,ClanDoc.class).getMappedResults();
        return new PageImpl<>(result , pageable , result.size());
    }
}
