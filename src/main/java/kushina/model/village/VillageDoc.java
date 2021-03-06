/*
 * @author Anshul Saraf
 */

package kushina.model.village;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "villages")
public class VillageDoc {

    @Id
    String id;
    VillageName name;
    String description;
    String image;
    VillageData data;
    VillageStatistic statistic;

}
