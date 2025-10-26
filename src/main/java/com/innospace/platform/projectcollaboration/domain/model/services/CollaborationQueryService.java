package com.innospace.platform.projectcollaboration.domain.model.services;

import com.innospace.platform.projectcollaboration.domain.model.aggregates.CollaborationRequest;
import com.innospace.platform.projectcollaboration.domain.model.queries.GetCollaborationRequestsByProjectIdQuery;

import java.util.List;

public interface CollaborationQueryService {
    List<CollaborationRequest> handle(GetCollaborationRequestsByProjectIdQuery query);
}
