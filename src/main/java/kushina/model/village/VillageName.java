/*
 * @author Anshul Saraf
 */

package kushina.model.village;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VillageName {
    String english;
    String kanji;
    String romaji;
}
