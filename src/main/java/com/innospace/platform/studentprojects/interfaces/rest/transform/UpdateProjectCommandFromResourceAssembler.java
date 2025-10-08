package com.innospace.platform.studentprojects.interfaces.rest.transform;


import com.innospace.platform.studentprojects.domain.model.commands.UpdateProjectCommand;
import com.innospace.platform.studentprojects.interfaces.rest.resources.UpdateProjectResource;

public class UpdateProjectCommandFromResourceAssembler {

    public static UpdateProjectCommand toCommand(Long id, UpdateProjectResource resource) {
        return new UpdateProjectCommand(
                id,
                resource.title(),
                resource.description()
        );
    }
}