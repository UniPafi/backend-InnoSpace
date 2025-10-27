package com.innospace.platform.studentapplications.interfaces.rest;

import com.innospace.platform.studentapplications.application.internal.services.StudentApplicationCardQueryService;
import com.innospace.platform.studentapplications.domain.model.commands.AcceptStudentApplicationCommand;
import com.innospace.platform.studentapplications.domain.model.commands.RejectStudentApplicationCommand;
import com.innospace.platform.studentapplications.domain.model.queries.GetStudentApplicationByIdQuery;
import com.innospace.platform.studentapplications.domain.model.services.StudentApplicationCommandService;
import com.innospace.platform.studentapplications.domain.model.services.StudentApplicationQueryService;
import com.innospace.platform.studentapplications.interfaces.rest.resources.CreateStudentApplicationResource;
import com.innospace.platform.studentapplications.interfaces.rest.resources.StudentApplicationCardResource;
import com.innospace.platform.studentapplications.interfaces.rest.resources.StudentApplicationResource;
import com.innospace.platform.studentapplications.interfaces.rest.transform.CreateStudentApplicationCommandFromResourceAssembler;
import com.innospace.platform.studentapplications.interfaces.rest.transform.StudentApplicationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/student-applications")
@Tag(name = "Student Applications", description = "Endpoints for student applications to opportunities")
public class StudentApplicationController {

    private final StudentApplicationCommandService commandService;
    private final StudentApplicationQueryService queryService;
    private final StudentApplicationCardQueryService cardQueryService;

    public StudentApplicationController(
            StudentApplicationCommandService commandService,
            StudentApplicationQueryService queryService,
            StudentApplicationCardQueryService cardQueryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.cardQueryService = cardQueryService;
    }

    @PostMapping
    public ResponseEntity<StudentApplicationResource> createApplication(@RequestBody CreateStudentApplicationResource resource) {
        var command = CreateStudentApplicationCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);

        if (result.isEmpty()) return ResponseEntity.badRequest().build();

        var created = StudentApplicationResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.created(URI.create("/api/v1/student-applications/" + created.id())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentApplicationResource> getApplicationById(@PathVariable Long id) {
        var query = new GetStudentApplicationByIdQuery(id);
        return queryService.handle(query)
                .map(StudentApplicationResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<StudentApplicationResource> acceptApplication(@PathVariable Long id) {
        var result = commandService.handle(new AcceptStudentApplicationCommand(id));
        return result
                .map(StudentApplicationResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<StudentApplicationResource> rejectApplication(@PathVariable Long id) {
        var result = commandService.handle(new RejectStudentApplicationCommand(id));
        return result
                .map(StudentApplicationResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/opportunities/{opportunityId}")
    public ResponseEntity<List<StudentApplicationCardResource>> getApplicationsByOpportunity(@PathVariable Long opportunityId) {
        var cards = cardQueryService.getApplicationsByOpportunity(opportunityId);
        return ResponseEntity.ok(cards);
    }
}
