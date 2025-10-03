package com.innospace.platform.profiles.interfaces.rest.assemblers;

import com.innospace.platform.profiles.domain.commands.CreateManagerProfileCommand;
import com.innospace.platform.profiles.interfaces.rest.resources.CreateManagerProfileResource;

public class CreateManagerProfileCommandFromResourceAssembler {
    public static CreateManagerProfileCommand toCommandFromResource(CreateManagerProfileResource resource) {
        return new CreateManagerProfileCommand(
                resource.userId(),
                resource.name(),
                resource.email(),
                resource.photoUrl(),
                resource.companyName(),
                resource.focusArea(),
                resource.companyTechnologies()
        );
    }
}