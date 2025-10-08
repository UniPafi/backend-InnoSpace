package com.innospace.platform.iam.interfaces.rest.transform;

import com.innospace.platform.iam.domain.model.commands.UpdateUserCommand;
import com.innospace.platform.iam.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long id, UpdateUserResource resource) {
        return new UpdateUserCommand(id, resource.email());
    }
}
