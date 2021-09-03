package webscraping.model.character;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharName implements Serializable {
    private static final long serialVersionUID = 1L;

    @TextIndexed(weight=3)String english;
    String kanji;
    String romaji;
    List<String> others;
}
