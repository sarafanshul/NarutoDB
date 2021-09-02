package webscraping.model.jutsu;

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
public class JutsuDebut {
    JutsuManga manga;
    JutsuAnime anime;
    String novel;
    String movie;
    String game;
    String ova;
    List<String> appears;
}
