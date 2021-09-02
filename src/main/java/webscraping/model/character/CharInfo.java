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
public class CharInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    CharName name;
    String description;
    List<String> images;
    List<String> family;
    List<String> natureTypes;
    List<String> uniqueTraits;
    List<String> jutsus;
    List<String> tools;
}
