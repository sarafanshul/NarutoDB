/*
 * @author Anshul Saraf
 */

package kushina.controller;

import kushina.model.tool.ToolDTO;
import kushina.service.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "tool")
public class ToolController {
    @Autowired
    ToolService toolService;

    @PostMapping(value = "/{name}")
    public ResponseEntity<Void> saveTool(@PathVariable String name) {
//        log.info("Starting get infos for tool: {}", name);
//        ToolDoc toolDoc = toolService.getToolInfo(name);
//        toolService.insert(toolDoc);
//        log.info("Tool {} infos saved.", name);
//        return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<ToolDTO> getTool(@PathVariable String name) {
//        log.info("Searching infos for tool: {}", name);
//        ToolDTO tool = toolService.getTool(name);
//        log.info("Infos getted for tool: {}", name);
//        return ResponseEntity.ok().body(tool);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
