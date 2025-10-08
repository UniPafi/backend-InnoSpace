package com.innospace.platform.profiles.interfaces.rest.assemblers;

import com.innospace.platform.profiles.domain.commands.UpdateManagerProfileCommand;
import com.innospace.platform.profiles.interfaces.rest.resources.UpdateManagerProfileResource;

public class UpdateManagerProfileCommandFromResourceAssembler {

    public static UpdateManagerProfileCommand toCommandFromResource(Long id, UpdateManagerProfileResource resource) {
        return new UpdateManagerProfileCommand(
                id,
                resource.name(),
                resource.photoUrl(),
                resource.description(),
                resource.phoneNumber(),
                resource.companyName(),
                resource.focusArea(),
                resource.companyTechnologies()
        );
    }
}
