package com.innospace.platform.projectcollaboration.application.internal.services;

import com.innospace.platform.projectcollaboration.domain.model.aggregates.CollaborationRequest;
import com.innospace.platform.projectcollaboration.domain.model.queries.GetCollaborationRequestsByProjectIdQuery;
import com.innospace.platform.projectcollaboration.domain.model.services.CollaborationQueryService;
import com.innospace.platform.projectcollaboration.infrastructure.jpa.repositories.CollaborationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaborationQueryServiceImpl implements CollaborationQueryService {

    private final CollaborationRepository repository;

    public CollaborationQueryServiceImpl(CollaborationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CollaborationRequest> handle(GetCollaborationRequestsByProjectIdQuery query) {
        return repository.findAllByProjectId(query.projectId());
    }
}