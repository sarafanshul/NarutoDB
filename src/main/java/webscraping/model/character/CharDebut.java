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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharDebut implements Serializable {
    private static final long serialVersionUID = 1L;

    CharManga manga;
    CharAnime anime;
    String novel;
    String movie;
    String game;
    String ova;
    List<String> appears;
}
