package webscraping.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import webscraping.model.tool.ToolDebut;
import webscraping.model.tool.ToolName;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToolDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    ToolName name;
    String description;
    ToolDebut debut;
    List<String> wielder;
//    String image;
}
