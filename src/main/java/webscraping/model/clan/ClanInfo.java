package webscraping.model.clan;

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
public class ClanInfo {
    String description;
    String image;
    List<String> appears;
    List<String> affiliation;
    List<String> kekkeiGenkai;
    List<String> members;
    List<String> jutsus;
}
