/*
 * @author Anshul Saraf
 */

package kushina.model.tool;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "tools")
public class ToolDoc {
    ToolName name;
    String description;
    String image;
    ToolDebut debut;
    List<String> wielder;
}
