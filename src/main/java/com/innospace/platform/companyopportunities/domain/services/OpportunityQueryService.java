package com.innospace.platform.companyopportunities.domain.services;


import com.innospace.platform.companyopportunities.domain.model.aggregates.Opportunity;
import com.innospace.platform.companyopportunities.domain.model.queries.GetAllCompanyOpportunitiesQuery;
import com.innospace.platform.companyopportunities.domain.model.queries.GetOpportunityByIdQuery;
import com.innospace.platform.companyopportunities.domain.model.queries.ValidateOpportunityOwnershipQuery;

import java.util.List;
import java.util.Optional;

public interface OpportunityQueryService {

    Optional<Opportunity> handle(GetOpportunityByIdQuery query);
    List<Opportunity> handle(GetAllCompanyOpportunitiesQuery query);
    boolean handle(ValidateOpportunityOwnershipQuery query);
}