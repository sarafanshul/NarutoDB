package webscraping.model.character;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import webscraping.model.character.*;

import java.io.Serializable;
import java.util.List;

@Deprecated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    String id;
    CharName name;
    String description;
    CharDebut debut;
    CharVoice voices;
    CharPersonal personal;
    CharRank charRank;
    List<String> family;
    List<String> natureTypes;
    List<String> uniqueTraits;
    List<String> jutsus;
    @JsonInclude(JsonInclude.Include.NON_EMPTY) //field returned empty in json = "tools" : [ ]
    List<String> tools;
    List<CharDatabook> databooks;
//    List<String> images;
}
