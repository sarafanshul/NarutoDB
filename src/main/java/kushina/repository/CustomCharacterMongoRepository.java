/*
 * @author Anshul Saraf
 */

package kushina.repository;

import kushina.model.character.CharacterDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomCharacterMongoRepository {

    /**
     * Finds the characters with ID in name
     *
     * @param name to match
     * @return List of characters found
     */
    List<CharacterDoc> getCharacterByMatchingEnglishName(String name);

    /**
     * Returns a Paged Result of Characters W.R.T Characters::jutsus::length , order DESC
     *
     * @param pageable Pageable object for pagination
     * @return paged result in order of Jutsu size , DESC
     */
    Page<CharacterDoc> findAllOrderByJutsusSizeDesc(Pageable pageable);

    /**
     * Returns a Paged Result of Characters W.R.T Characters::jutsus::length , order ASC
     *
     * @param pageable Pageable object for pagination
     * @return paged result in order of Jutsu size , ASC
     */
    Page<CharacterDoc> findAllOrderByJutsusSizeAsc(Pageable pageable);

}
