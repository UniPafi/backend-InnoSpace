package com.innospace.platform.studentapplications.interfaces.rest.resources;

public record StudentApplicationCardResource(
        Long id,
        Long opportunityId,
        String opportunityTitle,
        String opportunityDescription,
        Long studentId,
        String studentName,
        String studentPhotoUrl,
        String managerResponse
) {}