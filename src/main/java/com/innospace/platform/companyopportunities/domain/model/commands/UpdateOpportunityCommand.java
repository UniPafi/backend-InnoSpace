package com.innospace.platform.companyopportunities.domain.model.commands;


import java.util.List;

public record UpdateOpportunityCommand(
        Long opportunityId,
        String title,
        String description,
        String summary,
        String category,
        List<String> requirements
) {}