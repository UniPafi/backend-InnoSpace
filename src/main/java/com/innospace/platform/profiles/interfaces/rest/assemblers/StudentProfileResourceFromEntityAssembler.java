package com.innospace.platform.profiles.interfaces.rest.assemblers;

import com.innospace.platform.profiles.domain.aggregates.StudentProfile;
import com.innospace.platform.profiles.interfaces.rest.resources.StudentProfileResource;

import java.util.Set;

public class StudentProfileResourceFromEntityAssembler {
    public static StudentProfileResource toResourceFromEntity(StudentProfile entity) {
        return new StudentProfileResource(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getPhotoUrl(),
                entity.getDescription(),
                entity.getPhoneNumber(),
                entity.getPortfolioUrl(),
                entity.getSkills(),
                entity.getExperiences()
        );
    }
}