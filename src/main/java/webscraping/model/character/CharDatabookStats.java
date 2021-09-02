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
public class CharDatabookStats implements Serializable {
    private static final long serialVersionUID = 1L;

    Double ninjutsu;
    Double taijutsu;
    Double genjutsu;
    Double intelligence;
    Double strength;
    Double speed;
    Double stamina;
    Double handSeals;
    Double total;
}
