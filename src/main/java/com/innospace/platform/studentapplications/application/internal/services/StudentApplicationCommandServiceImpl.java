package com.innospace.platform.studentapplications.application.internal.services;

import com.innospace.platform.studentapplications.domain.model.aggregates.StudentApplication;
import com.innospace.platform.studentapplications.domain.model.commands.AcceptStudentApplicationCommand;
import com.innospace.platform.studentapplications.domain.model.commands.CreateStudentApplicationCommand;
import com.innospace.platform.studentapplications.domain.model.commands.RejectStudentApplicationCommand;
import com.innospace.platform.studentapplications.domain.model.services.StudentApplicationCommandService;
import com.innospace.platform.studentapplications.infrastructure.jpa.repositories.StudentApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentApplicationCommandServiceImpl implements StudentApplicationCommandService {

    private final StudentApplicationRepository repository;

    public StudentApplicationCommandServiceImpl(StudentApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<StudentApplication> handle(CreateStudentApplicationCommand command) {
        var application = new StudentApplication(command.opportunityId(), command.studentId());
        return Optional.of(repository.save(application));
    }

    @Override
    public Optional<StudentApplication> handle(AcceptStudentApplicationCommand command) {
        var app = repository.findById(command.applicationId());
        if (app.isEmpty()) return Optional.empty();
        app.get().accept();
        return Optional.of(repository.save(app.get()));
    }

    @Override
    public Optional<StudentApplication> handle(RejectStudentApplicationCommand command) {
        var app = repository.findById(command.applicationId());
        if (app.isEmpty()) return Optional.empty();
        app.get().reject();
        return Optional.of(repository.save(app.get()));
    }
}
