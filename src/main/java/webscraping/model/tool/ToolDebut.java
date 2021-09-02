package webscraping.model.tool;

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
public class ToolDebut {
    ToolManga manga;
    ToolAnime anime;
    String novel;
    String movie;
    String game;
    String ova;
    List<String> appears;
}
