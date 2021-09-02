package webscraping.model.team;

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
public class TeamInfo {
    List<String> leaders;
    List<String> members;
    List<String> affiliations;
}
