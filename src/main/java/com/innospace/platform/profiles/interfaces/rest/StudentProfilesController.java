package com.innospace.platform.profiles.interfaces.rest;

import com.innospace.platform.profiles.domain.queries.GetAllStudentProfilesQuery;
import com.innospace.platform.profiles.domain.queries.GetStudentProfileByIdQuery;
import com.innospace.platform.profiles.domain.services.StudentProfileCommandService;
import com.innospace.platform.profiles.domain.services.StudentProfileQueryService;
import com.innospace.platform.profiles.interfaces.rest.assemblers.CreateStudentProfileCommandFromResourceAssembler;
import com.innospace.platform.profiles.interfaces.rest.assemblers.StudentProfileResourceFromEntityAssembler;
import com.innospace.platform.profiles.interfaces.rest.assemblers.UpdateStudentProfileCommandFromResourceAssembler;
import com.innospace.platform.profiles.interfaces.rest.resources.CreateStudentProfileResource;
import com.innospace.platform.profiles.interfaces.rest.resources.StudentProfileResource;
import com.innospace.platform.profiles.interfaces.rest.resources.UpdateStudentProfileResource;
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
@RequestMapping(value = "/api/v1/student-profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Student Profiles", description = "Available Student Profile Endpoints")
public class StudentProfilesController {

    private final StudentProfileCommandService studentProfileCommandService;
    private final StudentProfileQueryService studentProfileQueryService;

    public StudentProfilesController(StudentProfileCommandService studentProfileCommandService,
                                     StudentProfileQueryService studentProfileQueryService) {
        this.studentProfileCommandService = studentProfileCommandService;
        this.studentProfileQueryService = studentProfileQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new student profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student profile created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<StudentProfileResource> createStudentProfile(@RequestBody CreateStudentProfileResource resource) {
        var command = CreateStudentProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = studentProfileCommandService.handle(command);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var createdProfile = profile.get();
        var resourceResponse = StudentProfileResourceFromEntityAssembler.toResourceFromEntity(createdProfile);
        return new ResponseEntity<>(resourceResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    @Operation(summary = "Get a student profile by ID")
    public ResponseEntity<StudentProfileResource> getStudentProfileById(@PathVariable Long profileId) {
        var query = new GetStudentProfileByIdQuery(profileId);
        var profile = studentProfileQueryService.handle(query);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var resourceResponse = StudentProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(resourceResponse);
    }

    @GetMapping
    @Operation(summary = "Get all student profiles")
    public ResponseEntity<List<StudentProfileResource>> getAllStudentProfiles() {
        var profiles = studentProfileQueryService.handle(new GetAllStudentProfilesQuery());
        if (profiles.isEmpty()) return ResponseEntity.notFound().build();
        var resources = profiles.stream()
                .map(StudentProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }



    @PutMapping("/{id}")
    @Operation(summary = "Update a student profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student profile updated"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<StudentProfileResource> updateStudentProfile(
            @PathVariable Long id,
            @RequestBody UpdateStudentProfileResource resource) {

        var command = UpdateStudentProfileCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var updatedProfile = studentProfileCommandService.handle(command);

        if (updatedProfile.isEmpty()) return ResponseEntity.notFound().build();

        var profileResource = StudentProfileResourceFromEntityAssembler.toResourceFromEntity(updatedProfile.get());
        return ResponseEntity.ok(profileResource);
    }
}




