package webscraping.model.jutsu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JutsuName {
    String english;
    String kanji;
    String romaji;
    String other;
}
