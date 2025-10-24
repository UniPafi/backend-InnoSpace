package com.innospace.platform.studentprojects.domain.model.commands;


public record CreateProjectCommand(
        Long studentId,
        String title,
        String description,
        String summary,
        String category
) {}