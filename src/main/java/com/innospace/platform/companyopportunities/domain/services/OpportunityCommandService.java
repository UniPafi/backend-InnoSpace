package com.innospace.platform.companyopportunities.domain.services;

import com.innospace.platform.companyopportunities.domain.model.aggregates.Opportunity;
import com.innospace.platform.companyopportunities.domain.model.commands.*;

import java.util.Optional;

public interface OpportunityCommandService {

    Optional<Opportunity> handle(CreateOpportunityCommand command);
    Optional<Opportunity> handle(UpdateOpportunityCommand command);
    Optional<Opportunity> handle(PublishOpportunityCommand command);
    Optional<Opportunity> handle(CloseOpportunityCommand command);
    void handle(DeleteOpportunityCommand command);
}