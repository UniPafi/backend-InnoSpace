package com.innospace.platform.studentprojects.domain.model.commands;

public record UpdateProjectCommand(
        Long id,
        String title,
        String description
) {}