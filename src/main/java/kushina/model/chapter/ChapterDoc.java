/*
 * @author Anshul Saraf
 */

package kushina.model.chapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "chapters")
public class ChapterDoc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    String id;
    ChapterName name;
    ChapterEpisode episode;
    String description;
    List<String> images;
    String arc;
    ChapterManga manga;
    ChapterMusic music;
    ChapterAirDate date;

}
