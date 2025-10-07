package com.innospace.platform.studentprojects.interfaces.rest.transform;

import com.innospace.platform.studentprojects.domain.model.commands.CreateProjectCommand;
import com.innospace.platform.studentprojects.interfaces.rest.resources.CreateProjectResource;

public class CreateProjectCommandFromResourceAssembler {

    public static CreateProjectCommand toCommand(CreateProjectResource resource) {
        return new CreateProjectCommand(
                resource.studentId(),
                resource.title(),
                resource.description()
        );
    }
}