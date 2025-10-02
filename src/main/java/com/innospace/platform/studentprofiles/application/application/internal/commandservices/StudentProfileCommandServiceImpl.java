package com.innospace.platform.studentprofiles.application.application.internal.commandservices;

import com.innospace.platform.studentprofiles.domain.aggregates.StudentProfile;
import com.innospace.platform.studentprofiles.domain.commands.CreateStudentProfileCommand;
import com.innospace.platform.studentprofiles.domain.services.StudentProfileCommandService;
import com.innospace.platform.studentprofiles.infrastructure.persistence.jpa.repositories.StudentProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentProfileCommandServiceImpl implements StudentProfileCommandService {

    private final StudentProfileRepository studentProfileRepository;

    public StudentProfileCommandServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public Optional<StudentProfile> handle(CreateStudentProfileCommand command) {
        if (studentProfileRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("Student profile with email already exists");
        }
        var studentProfile = new StudentProfile(command);
        studentProfileRepository.save(studentProfile);
        return Optional.of(studentProfile);
    }
}