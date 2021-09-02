package webscraping.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
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
public class JutsuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    String id;
    JutsuName name;
    String description;
    List<String> classification;
    String nature;
    String rank;
    List<String> type;
    String range;
    String handSeals;
    List<String> appears;
    JutsuDebut jutsuDebut;
    List<String> users;
//    String image;
}