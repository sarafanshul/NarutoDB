/*
 * @author Anshul Saraf
 */

package kushina.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import kushina.model.chapter.ChapterDoc;

import java.util.List;

@Repository
public interface ChapterRepository extends MongoRepository<ChapterDoc, String> {

    Page<ChapterDoc> findAllByOrderByEpisodeAbsoluteEpisodeNumberAsc(Pageable pageable);

    List<ChapterDoc> findAllByOrderByEpisodeAbsoluteEpisodeNumberAsc();

    @Query("{ \"$nor\": [ { \"manga.chapters\" : null } , { \"manga.chapters\" : {\"$size\": 0} } ] }")
    Page<ChapterDoc> findAllChaptersCannon(Pageable pageable );
}
