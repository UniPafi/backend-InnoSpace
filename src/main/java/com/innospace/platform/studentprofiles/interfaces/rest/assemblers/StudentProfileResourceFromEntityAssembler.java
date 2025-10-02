package com.innospace.platform.studentprofiles.interfaces.rest.assemblers;

import com.innospace.platform.studentprofiles.domain.aggregates.StudentProfile;
import com.innospace.platform.studentprofiles.interfaces.rest.resources.StudentProfileResource;

public class StudentProfileResourceFromEntityAssembler {
    public static StudentProfileResource toResourceFromEntity(StudentProfile entity) {
        return new StudentProfileResource(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getSkills(),
                entity.getEducation()
        );
    }
}