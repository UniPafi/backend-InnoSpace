package com.innospace.platform.companyopportunities.interfaces.rest.resources;

import java.util.List;

public record CreateOpportunityResource(
        Long companyId,
        String title,
        String description,
        String summary,
        String category,
        List<String> requirements
) {}