package com.innospace.platform.projectcollaboration.interfaces.rest;

import com.innospace.platform.projectcollaboration.domain.model.commands.AcceptCollaborationCommand;
import com.innospace.platform.projectcollaboration.domain.model.commands.RejectCollaborationCommand;
import com.innospace.platform.projectcollaboration.domain.model.queries.GetCollaborationRequestsByProjectIdQuery;
import com.innospace.platform.projectcollaboration.domain.model.services.CollaborationCommandService;
import com.innospace.platform.projectcollaboration.domain.model.services.CollaborationQueryService;
import com.innospace.platform.projectcollaboration.interfaces.rest.resources.CollaborationRequestResource;
import com.innospace.platform.projectcollaboration.interfaces.rest.resources.CreateCollaborationRequestResource;
import com.innospace.platform.projectcollaboration.interfaces.rest.transform.CollaborationRequestResourceFromEntityAssembler;
import com.innospace.platform.projectcollaboration.interfaces.rest.transform.CreateCollaborationRequestCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/collaborations")
@Tag(name = "Collaborations", description = "Endpoints for project-manager collaborations")
public class CollaborationController {

    private final CollaborationCommandService commandService;
    private final CollaborationQueryService queryService;

    public CollaborationController(CollaborationCommandService commandService, CollaborationQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<CollaborationRequestResource> createCollaboration(@RequestBody CreateCollaborationRequestResource resource) {
        var command = CreateCollaborationRequestCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var created = CollaborationRequestResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.created(URI.create("/api/v1/collaborations/" + created.id())).body(created);
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<CollaborationRequestResource> acceptCollaboration(@PathVariable Long id) {
        var result = commandService.handle(new AcceptCollaborationCommand(id));
        return result
                .map(CollaborationRequestResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<CollaborationRequestResource> rejectCollaboration(@PathVariable Long id) {
        var result = commandService.handle(new RejectCollaborationCommand(id));
        return result
                .map(CollaborationRequestResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<CollaborationRequestResource>> getByProject(@PathVariable Long projectId) {
        var query = new GetCollaborationRequestsByProjectIdQuery(projectId);
        var collaborations = queryService.handle(query)
                .stream()
                .map(CollaborationRequestResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(collaborations);
    }
}