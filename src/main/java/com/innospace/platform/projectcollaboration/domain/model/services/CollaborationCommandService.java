package com.innospace.platform.projectcollaboration.domain.model.services;

import com.innospace.platform.projectcollaboration.domain.model.aggregates.CollaborationRequest;
import com.innospace.platform.projectcollaboration.domain.model.commands.AcceptCollaborationCommand;
import com.innospace.platform.projectcollaboration.domain.model.commands.CreateCollaborationRequestCommand;
import com.innospace.platform.projectcollaboration.domain.model.commands.RejectCollaborationCommand;

import java.util.Optional;

public interface CollaborationCommandService {

    Optional<CollaborationRequest> handle(CreateCollaborationRequestCommand command);
    Optional<CollaborationRequest> handle(AcceptCollaborationCommand command);
    Optional<CollaborationRequest> handle(RejectCollaborationCommand command);
}
