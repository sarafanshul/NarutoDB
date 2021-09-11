package webscraping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webscraping.model.clan.ClanDoc;


public interface CustomClanMongoRepository {

    /**
     * Finds all Clans W.R.T their Clan::member::length
     *
     * @param pageable a Pageable object
     * @return Paged collection
     */
    Page<ClanDoc> getClansOrderedByMemberSize(Pageable pageable);

    /**
     * Finds all Clans W.R.T their Clan::jutsus::length
     *
     * @param pageable a Pageable object
     * @return Paged collection
     */
    Page<ClanDoc> getClansOrderedByJutsusSize(Pageable pageable);

}
