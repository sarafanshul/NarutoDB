package webscraping.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import webscraping.model.clan.ClanName;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "clans")
public class ClanDoc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    String id;
    ClanName name;
    String description;
    String image;
    List<String> appears;
    List<String> affiliation;
    List<String> kekkeiGenkai;
    List<String> members;
    List<String> jutsus;
}
