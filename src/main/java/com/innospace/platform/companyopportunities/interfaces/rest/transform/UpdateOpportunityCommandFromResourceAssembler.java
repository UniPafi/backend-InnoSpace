package com.innospace.platform.companyopportunities.interfaces.rest.transform;


import com.innospace.platform.companyopportunities.domain.model.commands.UpdateOpportunityCommand;
import com.innospace.platform.companyopportunities.interfaces.rest.resources.UpdateOpportunityResource;

public class UpdateOpportunityCommandFromResourceAssembler {
    public static UpdateOpportunityCommand toCommandFromResource(Long id, UpdateOpportunityResource resource) {
        return new UpdateOpportunityCommand(
                id,
                resource.title(),
                resource.description(),
                resource.requirements()
        );
    }
}