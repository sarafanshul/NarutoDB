/*
 * @author Anshul Saraf
 */

package kushina.model.team;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    String id;
    TeamName name;
    String description;
    TeamDebut debut;
    TeamInfo info;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<String> jutsus;
//    String image;
}
