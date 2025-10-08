package com.innospace.platform.profiles.domain.services;

import com.innospace.platform.profiles.domain.aggregates.StudentProfile;
import com.innospace.platform.profiles.domain.queries.GetAllStudentProfilesQuery;
import com.innospace.platform.profiles.domain.queries.GetStudentProfileByIdQuery;
import com.innospace.platform.profiles.domain.queries.GetStudentProfileByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface StudentProfileQueryService {
    Optional<StudentProfile> handle(GetStudentProfileByIdQuery query);
    Optional<StudentProfile> handle(GetStudentProfileByUserIdQuery query);
    List<StudentProfile> handle(GetAllStudentProfilesQuery query);
}