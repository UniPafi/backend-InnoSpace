package com.innospace.platform.companyopportunities.interfaces.rest.transform;

import com.innospace.platform.companyopportunities.domain.model.commands.CreateOpportunityCommand;
import com.innospace.platform.companyopportunities.interfaces.rest.resources.CreateOpportunityResource;

public class CreateOpportunityCommandFromResourceAssembler {
    public static CreateOpportunityCommand toCommandFromResource(CreateOpportunityResource resource) {
        return new CreateOpportunityCommand(
                resource.companyId(),
                resource.title(),
                resource.description(),
                resource.requirements()
        );
    }
}