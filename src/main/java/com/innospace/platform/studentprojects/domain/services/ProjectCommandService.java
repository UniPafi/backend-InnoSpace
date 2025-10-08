package com.innospace.platform.studentprojects.domain.services;

import com.innospace.platform.studentprojects.domain.model.aggregates.Project;
import com.innospace.platform.studentprojects.domain.model.commands.*;

import java.util.Optional;

public interface ProjectCommandService {
    Optional<Project> handle(CreateProjectCommand command);
    Optional<Project> handle(UpdateProjectCommand command);
    Optional<Project> handle(PublishProjectCommand command);
    Optional<Project> handle(FinalizeProjectCommand command);
    void handle(DeleteProjectCommand command);
}
