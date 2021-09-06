package webscraping.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import webscraping.model.team.TeamDoc;
import webscraping.model.team.TeamDTO;
import webscraping.model.team.TeamDebut;
import webscraping.model.team.TeamInfo;
import webscraping.model.team.TeamInfoBase;
import webscraping.model.team.TeamName;
import webscraping.repository.TeamRepository;
import webscraping.util.JsoupConnection;

import java.io.IOException;

import static webscraping.util.selector.team.TeamDebutSelector.getTeamDebut;
import static webscraping.util.selector.team.TeamInfoBaseSelector.getTeamInfoBase;
import static webscraping.util.selector.team.TeamInfoSelector.getInfoTeam;
import static webscraping.util.selector.team.TeamNameSelector.getTeamName;
import static webscraping.util.TeamInfoCheckNull.*;

@Slf4j
@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public TeamDoc getTeamInfo(String name) {
        Document doc = JsoupConnection.connectionInfo(name);
        log.info("Team url jsoup connected");
        TeamDoc teamDoc = new TeamDoc();
        if (doc != null) {
            try {

                TeamName teamName = getTeamName(doc);
                TeamInfo teamInfo = getInfoTeam(doc);
                TeamInfoBase teamInfoBase = getTeamInfoBase(doc);
                TeamDebut teamDebut = getTeamDebut(doc);

                teamDoc.setName(checkNullInfoName(teamName) ? null : teamName);
                teamDoc.setDescription(teamInfoBase.getDescription() == null ? null : teamInfoBase.getDescription());
                teamDoc.setImage(teamInfoBase.getImage() == null ? null : teamInfoBase.getImage());
                teamDoc.setDebut(checkNullDebut(teamDebut) ? null : teamDebut);
                teamDoc.setInfo(checkNullInfo(teamInfo) ? null : teamInfo);
                teamDoc.setJutsus(teamInfoBase.getTeamJutsus() == null ? null : teamInfoBase.getTeamJutsus());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.warn("Document is empty.");
        }
        return teamDoc;
    }

    public void insert(TeamDoc teamDoc) {
        if (getCheckTeam(teamDoc.getName().getEnglish()) == null) { //check if team already exists
            teamRepository.insert(teamDoc);
        } else {
            log.warn("Team already exists.");
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Team already exists.");
        }
    }

    public TeamDTO getCheckTeam(String name) {
        return teamRepository.findByNameEnglish(name);
    }

    public TeamDTO getTeam(String name) {
        if (teamRepository.findByNameEnglish(name) == null) {
            log.warn("Team not found.");
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Team not found.");
        }
        return teamRepository.findByNameEnglish(name);
    }
}
