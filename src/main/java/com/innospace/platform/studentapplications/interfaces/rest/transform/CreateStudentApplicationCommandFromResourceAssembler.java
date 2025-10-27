package com.innospace.platform.studentapplications.interfaces.rest.transform;

import com.innospace.platform.studentapplications.domain.model.commands.CreateStudentApplicationCommand;
import com.innospace.platform.studentapplications.interfaces.rest.resources.CreateStudentApplicationResource;

public class CreateStudentApplicationCommandFromResourceAssembler {
    public static CreateStudentApplicationCommand toCommandFromResource(CreateStudentApplicationResource resource) {
        return new CreateStudentApplicationCommand(
                resource.opportunityId(),
                resource.studentId()
        );
    }
}
