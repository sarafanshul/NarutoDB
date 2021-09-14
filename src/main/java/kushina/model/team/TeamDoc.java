/*
 * @author Anshul Saraf
 */

package kushina.model.team;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "teams")
public class TeamDoc {

    @Id
    String id;
    TeamName name;
    String description;
    String image;
    TeamDebut debut;
    TeamInfo info;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<String> jutsus;
}
