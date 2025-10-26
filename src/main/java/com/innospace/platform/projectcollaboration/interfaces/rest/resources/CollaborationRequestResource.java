package com.innospace.platform.projectcollaboration.interfaces.rest.resources;

public record CollaborationRequestResource(
        Long id,
        Long projectId,
        Long managerId,
        String status,
        String studentResponse
) {}