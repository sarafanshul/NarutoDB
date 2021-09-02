package webscraping.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import webscraping.model.village.VillageData;
import webscraping.model.village.VillageName;
import webscraping.model.village.VillageStatistic;

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
