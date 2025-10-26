package com.innospace.platform.projectcollaboration.application.internal.services;

import com.innospace.platform.projectcollaboration.domain.model.aggregates.CollaborationRequest;
import com.innospace.platform.projectcollaboration.domain.model.commands.AcceptCollaborationCommand;
import com.innospace.platform.projectcollaboration.domain.model.commands.CreateCollaborationRequestCommand;
import com.innospace.platform.projectcollaboration.domain.model.commands.RejectCollaborationCommand;
import com.innospace.platform.projectcollaboration.domain.model.services.CollaborationCommandService;
import com.innospace.platform.projectcollaboration.infrastructure.jpa.repositories.CollaborationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollaborationCommandServiceImpl implements CollaborationCommandService {

    private final CollaborationRepository repository;

    public CollaborationCommandServiceImpl(CollaborationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CollaborationRequest> handle(CreateCollaborationRequestCommand command) {
        var request = new CollaborationRequest(command.projectId(), command.managerId());
        return Optional.of(repository.save(request));
    }

    @Override
    public Optional<CollaborationRequest> handle(AcceptCollaborationCommand command) {
        var request = repository.findById(command.collaborationId());
        if (request.isEmpty()) return Optional.empty();
        request.get().accept();
        return Optional.of(repository.save(request.get()));
    }

    @Override
    public Optional<CollaborationRequest> handle(RejectCollaborationCommand command) {
        var request = repository.findById(command.collaborationId());
        if (request.isEmpty()) return Optional.empty();
        request.get().reject();
        return Optional.of(repository.save(request.get()));
    }
}