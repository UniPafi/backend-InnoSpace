package com.innospace.platform.studentapplications.interfaces.rest.resources;

public record StudentApplicationResource(
        Long id,
        Long opportunityId,
        Long studentId,
        String status,
        String managerResponse
) {}