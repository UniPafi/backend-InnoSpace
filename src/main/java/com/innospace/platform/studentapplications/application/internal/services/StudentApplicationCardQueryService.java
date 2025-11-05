package com.innospace.platform.studentapplications.application.internal.services;

import com.innospace.platform.companyopportunities.infrastructure.persistence.jpa.repositories.OpportunityRepository;
import com.innospace.platform.profiles.infrastructure.persistence.jpa.repositories.StudentProfileRepository;
import com.innospace.platform.studentapplications.domain.model.aggregates.StudentApplication;
import com.innospace.platform.studentapplications.infrastructure.jpa.repositories.StudentApplicationRepository;
import com.innospace.platform.studentapplications.interfaces.rest.resources.StudentApplicationCardResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentApplicationCardQueryService {

    private final StudentApplicationRepository repository;
    private final OpportunityRepository opportunityRepository;
    private final StudentProfileRepository studentRepository;

    public StudentApplicationCardQueryService(
            StudentApplicationRepository repository,
            OpportunityRepository opportunityRepository,
            StudentProfileRepository studentRepository
    ) {
        this.repository = repository;
        this.opportunityRepository = opportunityRepository;
        this.studentRepository = studentRepository;
    }

    public List<StudentApplicationCardResource> getApplicationsByOpportunity(Long opportunityId) {
        return mapApplications(repository.findAllByOpportunityId(opportunityId));
    }

    public List<StudentApplicationCardResource> getApplicationsByStudent(Long studentId) {
        return mapApplications(repository.findAllByStudentId(studentId));
    }

    private List<StudentApplicationCardResource> mapApplications(List<StudentApplication> applications) {
        return applications.stream()
                .map(app -> {
                    var opp = opportunityRepository.findById(app.getOpportunityId()).orElse(null);
                    var student = studentRepository.findById(app.getStudentId()).orElse(null);
                    if (opp == null || student == null) return null;

                    return new StudentApplicationCardResource(
                            app.getId(),
                            opp.getId(),
                            opp.getTitle(),
                            opp.getDescription(),
                            student.getId(),
                            student.getName(),
                            student.getPhotoUrl(),
                            app.getManagerResponse().name()
                    );
                })
                .filter(Objects::nonNull)
                .toList();
    }

}
