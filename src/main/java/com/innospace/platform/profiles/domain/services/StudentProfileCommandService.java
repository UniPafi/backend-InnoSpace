package com.innospace.platform.profiles.domain.services;

import com.innospace.platform.profiles.domain.aggregates.StudentProfile;
import com.innospace.platform.profiles.domain.commands.CreateStudentProfileCommand;
import com.innospace.platform.profiles.domain.commands.UpdateStudentProfileCommand;

import java.util.Optional;

public interface StudentProfileCommandService {
    Optional<StudentProfile> handle(CreateStudentProfileCommand command);
    Optional<StudentProfile> handle(UpdateStudentProfileCommand command);
}