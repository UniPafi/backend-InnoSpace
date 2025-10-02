package com.innospace.platform.studentprofiles.domain.services;

import com.innospace.platform.studentprofiles.domain.aggregates.StudentProfile;
import com.innospace.platform.studentprofiles.domain.commands.CreateStudentProfileCommand;

import java.util.Optional;

public interface StudentProfileCommandService {

    Optional<StudentProfile> handle(CreateStudentProfileCommand command);
}