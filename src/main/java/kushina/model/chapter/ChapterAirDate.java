/*
 * @author Anshul Saraf
 */

package kushina.model.chapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChapterAirDate implements Serializable {
    private static final long serialVersionUID = 1L;

    Date japanese;
    Date english;

}
