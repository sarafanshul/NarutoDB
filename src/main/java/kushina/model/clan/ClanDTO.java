/*
 * @author Anshul Saraf
 */

package kushina.model.clan;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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
public class ClanDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    ClanName clanName;
    String description;
    List<String> appears;
    List<String> affiliation;
    List<String> kekkeiGenkai;
    List<String> members;
    List<String> jutsus;
//    String image;
}
