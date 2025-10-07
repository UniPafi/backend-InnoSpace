package com.innospace.platform.companyopportunities.interfaces.rest;


import com.innospace.platform.companyopportunities.domain.model.commands.CloseOpportunityCommand;
import com.innospace.platform.companyopportunities.domain.model.commands.DeleteOpportunityCommand;
import com.innospace.platform.companyopportunities.domain.model.commands.PublishOpportunityCommand;
import com.innospace.platform.companyopportunities.domain.model.queries.GetAllCompanyOpportunitiesQuery;
import com.innospace.platform.companyopportunities.domain.model.queries.GetOpportunityByIdQuery;
import com.innospace.platform.companyopportunities.domain.services.OpportunityCommandService;
import com.innospace.platform.companyopportunities.domain.services.OpportunityQueryService;
import com.innospace.platform.companyopportunities.interfaces.rest.resources.CreateOpportunityResource;
import com.innospace.platform.companyopportunities.interfaces.rest.resources.OpportunityResource;
import com.innospace.platform.companyopportunities.interfaces.rest.resources.UpdateOpportunityResource;
import com.innospace.platform.companyopportunities.interfaces.rest.transform.CreateOpportunityCommandFromResourceAssembler;
import com.innospace.platform.companyopportunities.interfaces.rest.transform.OpportunityResourceFromEntityAssembler;
import com.innospace.platform.companyopportunities.interfaces.rest.transform.UpdateOpportunityCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/opportunities")
@Tag(name = "Opportunities", description = "Available Company Opportunities Endpoints")
public class OpportunityController {

    private final OpportunityCommandService commandService;
    private final OpportunityQueryService queryService;

    public OpportunityController(OpportunityCommandService commandService, OpportunityQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpportunityResource> getOpportunityById(@PathVariable Long id) {
        var query = new GetOpportunityByIdQuery(id);
        return queryService.handle(query)
                .map(OpportunityResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/company/{companyId}")
    public List<OpportunityResource> getAllCompanyOpportunities(@PathVariable Long companyId) {
        var query = new GetAllCompanyOpportunitiesQuery(companyId);
        return queryService.handle(query)
                .stream()
                .map(OpportunityResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
    }

    @PostMapping
    public ResponseEntity<OpportunityResource> createOpportunity(@RequestBody CreateOpportunityResource resource) {
        var command = CreateOpportunityCommandFromResourceAssembler.toCommandFromResource(resource);
        var opportunity = commandService.handle(command);
        if (opportunity.isEmpty()) return ResponseEntity.badRequest().build();

        var created = OpportunityResourceFromEntityAssembler.toResourceFromEntity(opportunity.get());
        return ResponseEntity.created(URI.create("/api/v1/opportunities/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpportunityResource> updateOpportunity(@PathVariable Long id, @RequestBody UpdateOpportunityResource resource) {
        var command = UpdateOpportunityCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var updated = commandService.handle(command);
        return updated
                .map(OpportunityResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<OpportunityResource> publishOpportunity(@PathVariable Long id) {
        var command = new PublishOpportunityCommand(id);
        var updated = commandService.handle(command);
        return updated
                .map(OpportunityResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<OpportunityResource> closeOpportunity(@PathVariable Long id) {
        var command = new CloseOpportunityCommand(id);
        var updated = commandService.handle(command);
        return updated
                .map(OpportunityResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpportunity(@PathVariable Long id) {
        commandService.handle(new DeleteOpportunityCommand(id));
        return ResponseEntity.noContent().build();
    }
}