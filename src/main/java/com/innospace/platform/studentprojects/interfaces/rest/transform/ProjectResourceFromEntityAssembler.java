package com.innospace.platform.studentprojects.interfaces.rest.transform;


import com.innospace.platform.studentprojects.domain.model.aggregates.Project;
import com.innospace.platform.studentprojects.interfaces.rest.resources.ProjectResource;

public class ProjectResourceFromEntityAssembler {

    public static ProjectResource toResource(Project entity) {
        return new ProjectResource(
                entity.getId(),
                entity.getStudentId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus()

        );
    }
}