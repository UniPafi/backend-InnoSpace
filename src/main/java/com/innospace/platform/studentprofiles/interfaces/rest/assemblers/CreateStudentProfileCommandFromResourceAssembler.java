package com.innospace.platform.studentprofiles.interfaces.rest.assemblers;

import com.innospace.platform.studentprofiles.domain.commands.CreateStudentProfileCommand;
import com.innospace.platform.studentprofiles.interfaces.rest.resources.CreateStudentProfileResource;

public class CreateStudentProfileCommandFromResourceAssembler {
    public static CreateStudentProfileCommand toCommandFromResource(CreateStudentProfileResource resource) {
        return new CreateStudentProfileCommand(
                resource.name(),
                resource.email(),
                resource.skills(),
                resource.education()
        );
    }
}