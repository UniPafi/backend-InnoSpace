package com.innospace.platform.companyopportunities.application.internal.queryservices;


import com.innospace.platform.companyopportunities.domain.model.aggregates.Opportunity;
import com.innospace.platform.companyopportunities.domain.model.queries.GetAllCompanyOpportunitiesQuery;
import com.innospace.platform.companyopportunities.domain.model.queries.GetOpportunityByIdQuery;
import com.innospace.platform.companyopportunities.domain.model.queries.ValidateOpportunityOwnershipQuery;
import com.innospace.platform.companyopportunities.domain.services.OpportunityQueryService;
import com.innospace.platform.companyopportunities.infrastructure.persistence.jpa.repositories.OpportunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpportunityQueryServiceImpl implements OpportunityQueryService {

    private final OpportunityRepository opportunityRepository;

    public OpportunityQueryServiceImpl(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    @Override
    public Optional<Opportunity> handle(GetOpportunityByIdQuery query) {
        return opportunityRepository.findById(query.opportunityId());
    }

    @Override
    public List<Opportunity> handle(GetAllCompanyOpportunitiesQuery query) {
        return opportunityRepository.findAllByCompanyId(query.companyId());
    }

    @Override
    public boolean handle(ValidateOpportunityOwnershipQuery query) {
        return opportunityRepository.findById(query.opportunityId())
                .map(opportunity -> opportunity.getCompanyId().equals(query.companyId()))
                .orElse(false);
    }
}