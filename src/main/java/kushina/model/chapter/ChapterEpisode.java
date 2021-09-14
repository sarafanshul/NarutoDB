/*
 * @author Anshul Saraf
 */

package kushina.model.chapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChapterEpisode {
    private static final long serialVersionUID = 1L;

    String series;
    Double episode;
    String previous;
    String next;
    int season;
    double absoluteEpisodeNumber;
}
