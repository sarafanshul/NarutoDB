package webscraping.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import webscraping.model.team.TeamDebut;
import webscraping.model.team.TeamInfo;
import webscraping.model.team.TeamName;

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
