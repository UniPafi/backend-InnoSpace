package com.innospace.platform.projectcollaboration.interfaces.rest.resources;

public record CollaborationCardResource(
        Long collaborationId,
        Long projectId,
        String projectTitle,
        String projectDescription,
        Long managerId,
        String managerName,
        String companyName,
        String managerPhotoUrl,
        String studentResponse
) {}