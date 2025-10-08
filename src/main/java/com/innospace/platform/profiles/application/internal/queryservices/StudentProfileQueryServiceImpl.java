package com.innospace.platform.profiles.application.internal.queryservices;


import com.innospace.platform.profiles.domain.aggregates.StudentProfile;
import com.innospace.platform.profiles.domain.queries.GetAllStudentProfilesQuery;
import com.innospace.platform.profiles.domain.queries.GetStudentProfileByIdQuery;
import com.innospace.platform.profiles.domain.queries.GetStudentProfileByUserIdQuery;
import com.innospace.platform.profiles.domain.services.StudentProfileQueryService;
import com.innospace.platform.profiles.infrastructure.persistence.jpa.repositories.StudentProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileQueryServiceImpl implements StudentProfileQueryService {

    private final StudentProfileRepository studentProfileRepository;

    public StudentProfileQueryServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public Optional<StudentProfile> handle(GetStudentProfileByIdQuery query) {
        return studentProfileRepository.findById(query.profileId());
    }



    @Override
    public List<StudentProfile> handle(GetAllStudentProfilesQuery query) {
        return studentProfileRepository.findAll();
    }
    @Override
    public Optional<StudentProfile> handle(GetStudentProfileByUserIdQuery query) {
        return studentProfileRepository.findByUserId(query.userId());
    }

}