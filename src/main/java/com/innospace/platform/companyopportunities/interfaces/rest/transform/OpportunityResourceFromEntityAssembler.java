package com.innospace.platform.companyopportunities.interfaces.rest.transform;

import com.innospace.platform.companyopportunities.domain.model.aggregates.Opportunity;
import com.innospace.platform.companyopportunities.interfaces.rest.resources.OpportunityResource;

public class OpportunityResourceFromEntityAssembler {
    public static OpportunityResource toResourceFromEntity(Opportunity entity) {
        return new OpportunityResource(
                entity.getId(),
                entity.getCompanyId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getRequirements(),
                entity.getStatus()
        );
    }
}