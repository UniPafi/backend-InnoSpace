package com.innospace.platform.projectcollaboration.interfaces.rest.transform;

import com.innospace.platform.projectcollaboration.domain.model.commands.CreateCollaborationRequestCommand;
import com.innospace.platform.projectcollaboration.interfaces.rest.resources.CreateCollaborationRequestResource;

public class CreateCollaborationRequestCommandFromResourceAssembler {
    public static CreateCollaborationRequestCommand toCommandFromResource(CreateCollaborationRequestResource resource) {
        return new CreateCollaborationRequestCommand(resource.projectId(), resource.managerId());
    }
}