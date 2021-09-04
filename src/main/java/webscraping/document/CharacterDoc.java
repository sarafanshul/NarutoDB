package webscraping.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import webscraping.model.character.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "characters")
public class CharacterDoc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    String id;
    CharName name;
    String description;
    List<String> images;
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
}
