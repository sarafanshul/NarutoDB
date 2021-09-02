package webscraping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webscraping.document.TeamDoc;
import webscraping.dto.TeamDTO;
import webscraping.service.TeamService;

@Slf4j
@RestController
@RequestMapping(value = "team")
public class TeamController {
    @Autowired
    TeamService teamService;

    @PostMapping(value = "/{name}")
    public ResponseEntity<Void> saveTeam(@PathVariable String name) {
        log.info("Starting get infos for team: {}", name);
        TeamDoc teamDoc = teamService.getTeamInfo(name);
        teamService.insert(teamDoc);
        log.info("Team {} infos saved.", name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<TeamDTO> getTeam(@PathVariable String name) {
        log.info("Searching infos for team: {}", name);
        TeamDTO team = teamService.getTeam(name);
        log.info("Infos getted for team: {}", name);
        return ResponseEntity.ok().body(team);
    }
}
