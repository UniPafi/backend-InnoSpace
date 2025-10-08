package com.innospace.platform.profiles.application.internal.commandservices;


import com.innospace.platform.profiles.domain.aggregates.StudentProfile;
import com.innospace.platform.profiles.domain.commands.CreateStudentProfileCommand;
import com.innospace.platform.profiles.domain.commands.UpdateStudentProfileCommand;
import com.innospace.platform.profiles.domain.services.StudentProfileCommandService;
import com.innospace.platform.profiles.infrastructure.persistence.jpa.repositories.StudentProfileRepository;
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

        var profile = new StudentProfile(command);
        return Optional.of(studentProfileRepository.save(profile));
    }

    @Override
    public Optional<StudentProfile> handle(UpdateStudentProfileCommand command) {
        var existing = studentProfileRepository.findById(command.id());
        if (existing.isEmpty()) return Optional.empty();

        var profile = existing.get();
        profile.updateProfile(command);
        return Optional.of(studentProfileRepository.save(profile));
    }
}