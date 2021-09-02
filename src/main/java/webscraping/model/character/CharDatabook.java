package webscraping.model.character;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharDatabook implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    String edition;
    CharDatabookStats stats;
}
