package com.innospace.platform.profiles.interfaces.rest;

import com.innospace.platform.profiles.domain.queries.GetAllManagerProfilesQuery;
import com.innospace.platform.profiles.domain.queries.GetManagerProfileByIdQuery;
import com.innospace.platform.profiles.domain.services.ManagerProfileCommandService;
import com.innospace.platform.profiles.domain.services.ManagerProfileQueryService;
import com.innospace.platform.profiles.interfaces.rest.assemblers.CreateManagerProfileCommandFromResourceAssembler;
import com.innospace.platform.profiles.interfaces.rest.assemblers.ManagerProfileResourceFromEntityAssembler;
import com.innospace.platform.profiles.interfaces.rest.assemblers.UpdateManagerProfileCommandFromResourceAssembler;
import com.innospace.platform.profiles.interfaces.rest.resources.CreateManagerProfileResource;
import com.innospace.platform.profiles.interfaces.rest.resources.ManagerProfileResource;
import com.innospace.platform.profiles.interfaces.rest.resources.UpdateManagerProfileResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/manager-profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Manager Profiles", description = "Available Manager Profile Endpoints")
public class ManagerProfilesController {

    private final ManagerProfileCommandService managerProfileCommandService;
    private final ManagerProfileQueryService managerProfileQueryService;

    public ManagerProfilesController(ManagerProfileCommandService managerProfileCommandService,
                                     ManagerProfileQueryService managerProfileQueryService) {
        this.managerProfileCommandService = managerProfileCommandService;
        this.managerProfileQueryService = managerProfileQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new manager profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Manager profile created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<ManagerProfileResource> createManagerProfile(@RequestBody CreateManagerProfileResource resource) {
        var command = CreateManagerProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = managerProfileCommandService.handle(command);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var createdProfile = profile.get();
        var resourceResponse = ManagerProfileResourceFromEntityAssembler.toResourceFromEntity(createdProfile);
        return new ResponseEntity<>(resourceResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    @Operation(summary = "Get a manager profile by ID")
    public ResponseEntity<ManagerProfileResource> getManagerProfileById(@PathVariable Long profileId) {
        var query = new GetManagerProfileByIdQuery(profileId);
        var profile = managerProfileQueryService.handle(query);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var resourceResponse = ManagerProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(resourceResponse);
    }

    @GetMapping
    @Operation(summary = "Get all manager profiles")
    public ResponseEntity<List<ManagerProfileResource>> getAllManagerProfiles() {
        var profiles = managerProfileQueryService.handle(new GetAllManagerProfilesQuery());
        if (profiles.isEmpty()) return ResponseEntity.notFound().build();
        var resources = profiles.stream()
                .map(ManagerProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a manager profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manager profile updated"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<ManagerProfileResource> updateManagerProfile(
            @PathVariable Long id,
            @RequestBody UpdateManagerProfileResource resource) {

        var command = UpdateManagerProfileCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var updatedProfile = managerProfileCommandService.handle(command);

        if (updatedProfile.isEmpty()) return ResponseEntity.notFound().build();

        var profileResource = ManagerProfileResourceFromEntityAssembler.toResourceFromEntity(updatedProfile.get());
        return ResponseEntity.ok(profileResource);
    }
}

