package com.innospace.platform.projectcollaboration.interfaces.rest;

import com.innospace.platform.projectcollaboration.domain.model.services.CollaborationCardQueryService;
import com.innospace.platform.projectcollaboration.interfaces.rest.resources.CollaborationCardResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/collaboration-cards")
@Tag(name = "Collaboration Cards", description = "Endpoints for viewing collaboration cards")
public class CollaborationCardController {

    private final CollaborationCardQueryService queryService;

    public CollaborationCardController(CollaborationCardQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<List<CollaborationCardResource>> getCardsByProject(@PathVariable Long projectId) {
        var cards = queryService.getCardsByProject(projectId);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/managers/{managerId}")
    public ResponseEntity<List<CollaborationCardResource>> getCardsByManager(@PathVariable Long managerId) {
        var cards = queryService.getCardsByManager(managerId);
        return ResponseEntity.ok(cards);
    }

}