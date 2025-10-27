package com.innospace.platform.studentapplications.application.internal.services;

import com.innospace.platform.studentapplications.domain.model.aggregates.StudentApplication;
import com.innospace.platform.studentapplications.domain.model.queries.GetStudentApplicationByIdQuery;
import com.innospace.platform.studentapplications.domain.model.queries.GetStudentApplicationsByOpportunityIdQuery;
import com.innospace.platform.studentapplications.domain.model.queries.GetStudentApplicationsByStudentIdQuery;
import com.innospace.platform.studentapplications.domain.model.services.StudentApplicationQueryService;
import com.innospace.platform.studentapplications.infrastructure.jpa.repositories.StudentApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentApplicationQueryServiceImpl implements StudentApplicationQueryService {

    private final StudentApplicationRepository repository;

    public StudentApplicationQueryServiceImpl(StudentApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentApplication> handle(GetStudentApplicationsByOpportunityIdQuery query) {
        return repository.findAllByOpportunityId(query.opportunityId());
    }

    @Override
    public List<StudentApplication> handle(GetStudentApplicationsByStudentIdQuery query) {
        return repository.findAllByStudentId(query.studentId());
    }

    @Override
    public Optional<StudentApplication> handle(GetStudentApplicationByIdQuery query) {
        return repository.findById(query.applicationId());
    }
}
