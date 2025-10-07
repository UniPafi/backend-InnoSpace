package com.innospace.platform.studentprojects.interfaces.rest;


import com.innospace.platform.studentprojects.application.internal.commandservices.ProjectCommandServiceImpl;
import com.innospace.platform.studentprojects.application.internal.queryservices.ProjectQueryServiceImpl;
import com.innospace.platform.studentprojects.domain.model.commands.DeleteProjectCommand;
import com.innospace.platform.studentprojects.domain.model.commands.FinalizeProjectCommand;
import com.innospace.platform.studentprojects.domain.model.commands.PublishProjectCommand;
import com.innospace.platform.studentprojects.domain.model.queries.GetAllStudentProjectsQuery;
import com.innospace.platform.studentprojects.domain.model.queries.GetProjectByIdQuery;
import com.innospace.platform.studentprojects.domain.services.ProjectQueryService;
import com.innospace.platform.studentprojects.interfaces.rest.resources.CreateProjectResource;
import com.innospace.platform.studentprojects.interfaces.rest.resources.ProjectResource;
import com.innospace.platform.studentprojects.interfaces.rest.resources.UpdateProjectResource;
import com.innospace.platform.studentprojects.interfaces.rest.transform.CreateProjectCommandFromResourceAssembler;
import com.innospace.platform.studentprojects.interfaces.rest.transform.ProjectResourceFromEntityAssembler;
import com.innospace.platform.studentprojects.interfaces.rest.transform.UpdateProjectCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects")
@Tag(name = "Projects", description = "Available Student Project Endpoints")
public class ProjectController {

    private final ProjectCommandServiceImpl projectCommandService;
    private final ProjectQueryServiceImpl projectQueryService;

    public ProjectController(ProjectCommandServiceImpl projectCommandService,
                             ProjectQueryServiceImpl projectQueryService) {
        this.projectCommandService = projectCommandService;
        this.projectQueryService = projectQueryService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a project by ID")
    public ResponseEntity<ProjectResource> getProjectById(@PathVariable Long id) {
        var query = new GetProjectByIdQuery(id);
        var project = projectQueryService.handle(query);

        return project.map(value ->
                ResponseEntity.ok(ProjectResourceFromEntityAssembler.toResource(value))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get projects by student ID")
    public ResponseEntity<List<ProjectResource>> getAllStudentProjects(@PathVariable Long studentId) {
        var query = new GetAllStudentProjectsQuery(studentId);
        var projects = projectQueryService.handle(query)
                .stream()
                .map(ProjectResourceFromEntityAssembler::toResource)
                .toList();

        return ResponseEntity.ok(projects);
    }

    @PostMapping
    public ResponseEntity<ProjectResource> createProject(@RequestBody CreateProjectResource resource) {
        var command = CreateProjectCommandFromResourceAssembler.toCommand(resource);
        var project = projectCommandService.handle(command);

        if (project.isEmpty()) return ResponseEntity.badRequest().build();

        var created = ProjectResourceFromEntityAssembler.toResource(project.get());
        return ResponseEntity.created(URI.create("/api/v1/projects/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResource> updateProject(
            @PathVariable Long id,
            @RequestBody UpdateProjectResource resource
    ) {
        var command = UpdateProjectCommandFromResourceAssembler.toCommand(id, resource);
        var updated = projectCommandService.handle(command);

        return updated
                .map(value -> ResponseEntity.ok(ProjectResourceFromEntityAssembler.toResource(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<?> publishProject(@PathVariable Long id) {
        var command = new PublishProjectCommand(id);
        var result = projectCommandService.handle(command);
        return result.isPresent() ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/finalize")
    public ResponseEntity<?> finalizeProject(@PathVariable Long id) {
        var command = new FinalizeProjectCommand(id);
        var result = projectCommandService.handle(command);
        return result.isPresent() ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        var command = new DeleteProjectCommand(id);
        projectCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}

