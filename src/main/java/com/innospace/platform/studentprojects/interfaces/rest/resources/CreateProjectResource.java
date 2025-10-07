package com.innospace.platform.studentprojects.interfaces.rest.resources;

public record CreateProjectResource(
        Long studentId,
        String title,
        String description
) {}