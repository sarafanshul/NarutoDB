/*
 * @author Anshul Saraf
 */

package kushina.repository;

import kushina.model.chapter.ChapterDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomChapterMongoRepository {

    /**
     * Aggregation Pipeline to create page:
     * <ol>
     *      <li>with <i>range<i/> w.r.t <b>chapter.episode.absoluteEpisodeNumber<b/></li>
     *      <li>with <i>cannon<i/> episodes</li>
     *      <li>with a custom sort <i>order<i/></li>
     * </ol>
     *
     * @param rangeL    starting episode number (inclusive)
     * @param rangeR    end episode number (inclusive)
     * @param cannon    filter cannon chapters
     * @param sortOrder <b>"1"</b> <i>ASC</i> , <b>"-1"</b> <i>DESC</i>
     * @param pageable  page param
     * @return Paged data from DB
     */
    Page<ChapterDoc> findAllByOrderByEpisodeRangeSorted(int rangeL, int rangeR, boolean cannon, int sortOrder, Pageable pageable);
}
