package com.innospace.platform.profiles.interfaces.rest.assemblers;

import com.innospace.platform.profiles.domain.commands.UpdateStudentProfileCommand;
import com.innospace.platform.profiles.interfaces.rest.resources.UpdateStudentProfileResource;

public class UpdateStudentProfileCommandFromResourceAssembler {

    public static UpdateStudentProfileCommand toCommandFromResource(Long id, UpdateStudentProfileResource resource) {
        return new UpdateStudentProfileCommand(
                id,
                resource.name(),
                resource.photoUrl(),
                resource.description(),
                resource.phoneNumber(),
                resource.skills(),
                resource.experiences()
        );
    }
}
