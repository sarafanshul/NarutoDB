package webscraping.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import webscraping.model.jutsu.JutsuDebut;
import webscraping.model.jutsu.JutsuName;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "jutsus")
public class JutsuDoc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    String id;
    JutsuName name;
    String description;
    String image;
    List<String> classification;
    String nature;
    String rank;
    List<String> type;
    String range;
    String handSeals;
    JutsuDebut debut;
    List<String> users;
}
