/*
 * @author Anshul Saraf
 */

package kushina.model.tool;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToolAnime {
    String name;
    Double episode;
}
