/*
 * @author Anshul Saraf
 */

package kushina.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import kushina.model.clan.ClanDoc;
import kushina.model.clan.ClanInfo;
import kushina.model.clan.ClanName;
import kushina.repository.ClanRepository;
import kushina.util.JsoupConnection;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static kushina.util.selector.clan.ClanInfoSelector.getInfoData;
import static kushina.util.selector.clan.ClanNameSelector.getNameClan;

@Slf4j
@Service
public class ClanService {

    @Autowired
    ClanRepository clanRepository;

    private ClanDoc getClanInfo(String name) {
        Document doc = JsoupConnection.connectionInfo(name);
        log.info("Clan url jsoup connected for {}", name);
        ClanDoc clanDoc = new ClanDoc();
        if (doc != null) {
            try {
                ClanName clanName = getNameClan(doc);
                ClanInfo clanInfo = getInfoData(doc);

                clanDoc.setId(name);
                clanDoc.setName(clanName);
                clanDoc.setDescription(clanInfo.getDescription() == null ? null : clanInfo.getDescription());
                clanDoc.setImage(clanInfo.getImage() == null ? null : clanInfo.getImage());
                clanDoc.setAppears(clanInfo.getAppears() == null ? null : clanInfo.getAppears());
                clanDoc.setAffiliation(clanInfo.getAffiliation() == null ? null : clanInfo.getAffiliation());
                clanDoc.setKekkeiGenkai(clanInfo.getKekkeiGenkai() == null ? null : clanInfo.getKekkeiGenkai());
                clanDoc.setMembers(clanInfo.getMembers() == null ? null : clanInfo.getMembers());
                clanDoc.setJutsus(clanInfo.getJutsus() == null ? null : clanInfo.getJutsus());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.warn("Document is empty.");
        }
        return clanDoc;
    }

    public ClanDoc insert(String name) {
        if (!getCheckClanId(name).isPresent()) { //check if clan already exists
            return clanRepository.insert(getClanInfo(name));
        } else {
            log.warn("Clan already exists.");
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Clan already exists.");
        }
    }

    public Optional<ClanDoc> getCheckClanId(String id) {
        return clanRepository.findById(id);
    }

    public ClanDoc getClan(String name) {
        Optional<ClanDoc> obj = getCheckClanId(name);
        if (!obj.isPresent()) {
            log.warn("Clan not found.");
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Clan not found.");
        }
        return obj.get();
    }

    public List<ClanDoc> getClanByNameEnglishRegex(String name) {
        if (name.length() < 1) {
            log.warn("String length too short.");
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Length too short"
            );
        }
        return clanRepository.findByNameEnglishRegex(name);
    }

    public List<ClanDoc> getAllClans() {
        return clanRepository.findAll();
    }

    public Page<ClanDoc> getAllClansPaged(Pageable pageable) {
        return clanRepository.findAll(pageable);
    }

    public Page<ClanDoc> getClansOrderedByMemberSize(Pageable pageable) {
        return clanRepository.getClansOrderedByMemberSize(pageable);
    }

    public Page<ClanDoc> getClansOrderedByJutsusSize(Pageable pageable) {
        return clanRepository.getClansOrderedByJutsusSize(pageable);
    }

}
