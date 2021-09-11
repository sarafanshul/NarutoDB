package webscraping.model.village;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Deprecated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VillageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    String id;
    VillageName name;
    String description;
    VillageData data;
    VillageStatistic statistic;
//    String image;
}
