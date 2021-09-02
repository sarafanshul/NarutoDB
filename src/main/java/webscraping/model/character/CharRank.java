package webscraping.model.character;

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
public class CharRank implements Serializable {
    private static final long serialVersionUID = 1L;

    List<String> ninjaRank;
    String ninjaRegistration;
    Integer academyGradAge;
    Integer chuninPromAge;
}
