package com.innospace.platform.projectcollaboration.interfaces.rest.transform;

import com.innospace.platform.projectcollaboration.domain.model.aggregates.CollaborationRequest;
import com.innospace.platform.projectcollaboration.interfaces.rest.resources.CollaborationRequestResource;

public class CollaborationRequestResourceFromEntityAssembler {
    public static CollaborationRequestResource toResourceFromEntity(CollaborationRequest entity) {
        return new CollaborationRequestResource(
                entity.getId(),
                entity.getProjectId(),
                entity.getManagerId(),
                entity.getStatus().name(),
                entity.getStudentResponse().name()
        );
    }
}
