package com.innospace.platform.studentprojects.interfaces.rest.resources;

import com.innospace.platform.studentprojects.domain.model.valueobjects.ProjectStatus;

public record ProjectResource(
        Long id,
        Long studentId,
        String title,
        String description,
        ProjectStatus status

) {}