package com.innospace.platform.studentprofiles.interfaces.rest;


import com.innospace.platform.studentprofiles.domain.services.StudentProfileCommandService;
import com.innospace.platform.studentprofiles.interfaces.rest.assemblers.CreateStudentProfileCommandFromResourceAssembler;
import com.innospace.platform.studentprofiles.interfaces.rest.assemblers.StudentProfileResourceFromEntityAssembler;
import com.innospace.platform.studentprofiles.interfaces.rest.resources.CreateStudentProfileResource;
import com.innospace.platform.studentprofiles.interfaces.rest.resources.StudentProfileResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/student-profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Student Profiles", description = "Available Student Profile Endpoints")
public class StudentProfilesController {

    private final StudentProfileCommandService studentProfileCommandService;


    public StudentProfilesController(StudentProfileCommandService studentProfileCommandService) {
        this.studentProfileCommandService = studentProfileCommandService;

    }

    @PostMapping
    @Operation(summary = "Create a new Student Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student profile created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<StudentProfileResource> createStudentProfile(@RequestBody CreateStudentProfileResource resource) {
        var command = CreateStudentProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = studentProfileCommandService.handle(command);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var createdProfile = profile.get();
        var profileResource = StudentProfileResourceFromEntityAssembler.toResourceFromEntity(createdProfile);
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }
}