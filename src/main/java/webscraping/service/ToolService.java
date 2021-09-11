package webscraping.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import webscraping.model.tool.*;
import webscraping.repository.ToolRepository;
import webscraping.util.JsoupConnection;
import webscraping.util.ToolInfoCheckNull;

import java.io.IOException;

import static webscraping.util.selector.tool.ToolDebutSelector.getToolDebut;
import static webscraping.util.selector.tool.ToolInfoSelector.getInfoTool;
import static webscraping.util.selector.tool.ToolNameSelector.getToolName;

@Slf4j
@Service
public class ToolService {

    @Autowired
    ToolRepository toolRepository;

    public ToolDoc getToolInfo(String name) {
        Document doc = JsoupConnection.connectionInfo(name);
        log.info("Tool url jsoup connected");
        ToolDoc toolDoc = new ToolDoc();
        if (doc != null) {
            try {

                ToolName toolName = getToolName(doc);
                ToolInfo toolInfo = getInfoTool(doc);
                ToolDebut toolDebut = getToolDebut(doc);
                toolDoc.setName(ToolInfoCheckNull.checkNullInfoName(toolName) ? null : toolName);
                toolDoc.setDescription(toolInfo.getDescription() == null ? null : toolInfo.getDescription());
                toolDoc.setImage(toolInfo.getImage() == null ? null : toolInfo.getImage());
                toolDoc.setDebut(ToolInfoCheckNull.checkNullDebut(toolDebut) ? null : toolDebut);
                toolDoc.setWielder(toolInfo.getWielder() == null ? null : toolInfo.getWielder());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.warn("Document is empty.");
        }
        return toolDoc;
    }

    public void insert(ToolDoc toolDoc) {
        if (getCheckTool(toolDoc.getName().getEnglish()) == null) { //check if tool already exists
            toolRepository.insert(toolDoc);
        } else {
            log.warn("Tool already exists.");
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Tool already exists.");
        }
    }

    public ToolDTO getCheckTool(String name) {
        return toolRepository.findByNameEnglish(name);
    }

    public ToolDTO getTool(String name) {
        if (toolRepository.findByNameEnglish(name) == null) {
            log.warn("Tool not found.");
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tool not found.");
        }
        return toolRepository.findByNameEnglish(name);
    }
}
