package com.innospace.platform.projectcollaboration.domain.model.commands;

public record CreateCollaborationRequestCommand(Long projectId, Long managerId) {}