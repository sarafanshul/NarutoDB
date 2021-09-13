package webscraping.model.chapter;

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
public class ChapterInfo {
    private static final long serialVersionUID = 1L;

    Double id;
    String description;
    List<String> images;
    String arc;

}
