package com.innospace.platform.profiles.interfaces.rest.assemblers;

import com.innospace.platform.profiles.domain.commands.CreateStudentProfileCommand;
import com.innospace.platform.profiles.interfaces.rest.resources.CreateStudentProfileResource;

public class CreateStudentProfileCommandFromResourceAssembler {
    public static CreateStudentProfileCommand toCommandFromResource(CreateStudentProfileResource resource) {
        return new CreateStudentProfileCommand(
                resource.userId(),
                resource.name(),
                resource.photoUrl(),
                resource.description(),
                resource.phoneNumber(),
                resource.skills(),
                resource.experiences()
        );
    }
}