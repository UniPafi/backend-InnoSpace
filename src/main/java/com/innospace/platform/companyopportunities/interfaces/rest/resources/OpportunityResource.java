package com.innospace.platform.companyopportunities.interfaces.rest.resources;

import com.innospace.platform.companyopportunities.domain.model.valueobjects.OpportunityStatus;

import java.util.List;

public record OpportunityResource(
        Long id,
        Long companyId,
        String title,
        String description,
        String summary,
        String category,
        List<String> requirements,
        OpportunityStatus status
) {}