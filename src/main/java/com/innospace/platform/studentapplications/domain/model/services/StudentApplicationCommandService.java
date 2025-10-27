package com.innospace.platform.studentapplications.domain.model.services;

import com.innospace.platform.studentapplications.domain.model.aggregates.StudentApplication;
import com.innospace.platform.studentapplications.domain.model.commands.AcceptStudentApplicationCommand;
import com.innospace.platform.studentapplications.domain.model.commands.CreateStudentApplicationCommand;
import com.innospace.platform.studentapplications.domain.model.commands.RejectStudentApplicationCommand;

import java.util.Optional;

public interface StudentApplicationCommandService {
    Optional<StudentApplication> handle(CreateStudentApplicationCommand command);
    Optional<StudentApplication> handle(AcceptStudentApplicationCommand command);
    Optional<StudentApplication> handle(RejectStudentApplicationCommand command);
}