package com.innospace.platform.studentapplications.interfaces.rest;

import com.innospace.platform.studentapplications.application.internal.services.StudentApplicationCardQueryService;
import com.innospace.platform.studentapplications.interfaces.rest.resources.StudentApplicationCardResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/opportunity-cards")
@Tag(name = "Student Application Cards", description = "Endpoints for viewing student applications cards")
public class StudentApplicationCardController {

    private final StudentApplicationCardQueryService queryService;

    public StudentApplicationCardController(StudentApplicationCardQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/opportunities/{opportunityId}")
    public ResponseEntity<List<StudentApplicationCardResource>> getApplicationCards(@PathVariable Long opportunityId) {
        var cards = queryService.getApplicationsByOpportunity(opportunityId);
        if (cards.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cards);
    }


    @GetMapping("/students/{studentId}")
    public ResponseEntity<List<StudentApplicationCardResource>> getApplicationsByStudent(@PathVariable Long studentId) {
        var cards = queryService.getApplicationsByStudent(studentId);
        if (cards.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cards);
    }




}