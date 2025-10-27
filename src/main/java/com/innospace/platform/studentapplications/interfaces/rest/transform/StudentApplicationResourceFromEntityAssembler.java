package com.innospace.platform.studentapplications.interfaces.rest.transform;

import com.innospace.platform.studentapplications.domain.model.aggregates.StudentApplication;
import com.innospace.platform.studentapplications.interfaces.rest.resources.StudentApplicationResource;

public class StudentApplicationResourceFromEntityAssembler {
    public static StudentApplicationResource toResourceFromEntity(StudentApplication entity) {
        return new StudentApplicationResource(
                entity.getId(),
                entity.getOpportunityId(),
                entity.getStudentId(),
                entity.getStatus().name(),
                entity.getManagerResponse().name()
        );
    }
}
