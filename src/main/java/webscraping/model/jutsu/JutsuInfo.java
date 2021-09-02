package webscraping.model.jutsu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JutsuInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    String description;
    String image;
    List<String> classification;
    String nature;
    String rank;
    List<String> type;
    String range;
    String handSeals;
    List<String> users;
}
