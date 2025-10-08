package com.innospace.platform.companyopportunities.application.internal.commandservices;


import com.innospace.platform.companyopportunities.domain.model.aggregates.Opportunity;
import com.innospace.platform.companyopportunities.domain.model.commands.*;
import com.innospace.platform.companyopportunities.domain.services.OpportunityCommandService;
import com.innospace.platform.companyopportunities.infrastructure.persistence.jpa.repositories.OpportunityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OpportunityCommandServiceImpl implements OpportunityCommandService {

    private final OpportunityRepository opportunityRepository;

    public OpportunityCommandServiceImpl(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    @Override
    public Optional<Opportunity> handle(CreateOpportunityCommand command) {
        var opportunity = new Opportunity(command);
        var created = opportunityRepository.save(opportunity);
        return Optional.of(created);
    }

    @Override
    public Optional<Opportunity> handle(UpdateOpportunityCommand command) {
        var existing = opportunityRepository.findById(command.opportunityId());
        if (existing.isEmpty()) return Optional.empty();

        var opportunity = existing.get();
        opportunity.update(command);
        return Optional.of(opportunityRepository.save(opportunity));
    }

    @Override
    public Optional<Opportunity> handle(PublishOpportunityCommand command) {
        var existing = opportunityRepository.findById(command.opportunityId());
        if (existing.isEmpty()) return Optional.empty();

        var opportunity = existing.get();
        opportunity.publish();
        return Optional.of(opportunityRepository.save(opportunity));
    }

    @Override
    public Optional<Opportunity> handle(CloseOpportunityCommand command) {
        var existing = opportunityRepository.findById(command.opportunityId());
        if (existing.isEmpty()) return Optional.empty();

        var opportunity = existing.get();
        opportunity.close();
        return Optional.of(opportunityRepository.save(opportunity));
    }

    @Override
    public void handle(DeleteOpportunityCommand command) {
        opportunityRepository.deleteById(command.opportunityId());
    }
}