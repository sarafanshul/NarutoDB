/*
 * @author Anshul Saraf
 */

package kushina.repository;

import kushina.model.chapter.ChapterDoc;
import org.bson.BsonNull;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import java.util.Arrays;
import java.util.List;

public class CustomChapterMongoRepositoryImpl implements CustomChapterMongoRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public final String COLLECTION_NAME = "chapters";

    public static final int SORT_ASC = 1;
    public static final int SORT_DESC = -1;

    @Override
    public Page<ChapterDoc> findAllByOrderByEpisodeRangeSorted(int rangeL, int rangeR, boolean cannon, int sortOrder, Pageable pageable) {
        Aggregation pipeline;
        // remove if else to AND in query instead.
        if( cannon )
             pipeline = Aggregation.newAggregation(
                cannonMatch -> new Document("$match",
                    new Document("$nor", Arrays.asList(new Document("manga.chapters",
                            new BsonNull()),
                        new Document("manga.chapters",
                            new Document("$size", 0L))))),

                rangeMatch -> new Document("$match",
                    new Document("episode.absoluteEpisodeNumber",
                        new Document("$lte", rangeR)
                            .append("$gte", rangeL))),

                sort -> new Document("$sort",
                    new Document("episode.absoluteEpisodeNumber", sortOrder)),

                Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize()),
                Aggregation.limit(pageable.getPageSize())
            );
        else
            pipeline = Aggregation.newAggregation(
                rangeMatch -> new Document("$match",
                    new Document("episode.absoluteEpisodeNumber",
                        new Document("$lte", rangeR)
                            .append("$gte", rangeL))),

                sort -> new Document("$sort",
                    new Document("episode.absoluteEpisodeNumber", sortOrder)),

                Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize()),
                Aggregation.limit(pageable.getPageSize())
            );

        List<ChapterDoc> results = mongoTemplate.aggregate(pipeline ,COLLECTION_NAME ,ChapterDoc.class).getMappedResults();

        return new PageImpl<>(results, pageable, results.size());
    }
}
