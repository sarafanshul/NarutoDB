package webscraping.model.character;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharPersonal implements Serializable {
    private static final long serialVersionUID = 1L;
    String birthDate;
    String sex;
    List<String> age;
    String specie;
    String status;
    List<String> height;
    List<String> weight;
    String bloodType;
    List<String> kekkeiGenkai;
    List<String> kekkeiMora;
    List<String> classification;
    List<String> tailedBeast;
    @JsonInclude(JsonInclude.Include.NON_EMPTY) //field returned empty in json = "occupation" : [ ]
    List<String> occupation;
    List<String> affiliation;
    List<String> team;
    List<String> partner;
    List<String> clan;
}
