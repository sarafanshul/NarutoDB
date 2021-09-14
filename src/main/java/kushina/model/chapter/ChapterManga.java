/*
 * @author Anshul Saraf
 */

package kushina.model.chapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChapterManga {

    List<String> chapters;

}
