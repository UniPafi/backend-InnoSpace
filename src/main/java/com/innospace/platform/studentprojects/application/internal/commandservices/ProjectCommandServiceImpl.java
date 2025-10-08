package com.innospace.platform.studentprojects.application.internal.commandservices;

import com.innospace.platform.studentprojects.domain.model.aggregates.Project;
import com.innospace.platform.studentprojects.domain.model.commands.*;
import com.innospace.platform.studentprojects.domain.services.ProjectCommandService;
import com.innospace.platform.studentprojects.infrastructure.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {

    private final ProjectRepository projectRepository;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        var project = new Project(
                command.studentId(),
                command.title(),
                command.description()
        );
        return Optional.of(projectRepository.save(project));
    }

    @Override
    public Optional<Project> handle(UpdateProjectCommand command) {
        var existing = projectRepository.findById(command.id());
        if (existing.isEmpty()) return Optional.empty();

        var project = existing.get();
        project.update(command);
        return Optional.of(projectRepository.save(project));
    }

    @Override
    public Optional<Project> handle(PublishProjectCommand command) {
        var existing = projectRepository.findById(command.id());
        if (existing.isEmpty()) return Optional.empty();

        var project = existing.get();
        project.publish();
        return Optional.of(projectRepository.save(project));
    }

    @Override
    public Optional<Project> handle(FinalizeProjectCommand command) {
        var existing = projectRepository.findById(command.id());
        if (existing.isEmpty()) return Optional.empty();

        var project = existing.get();
        project.finalizeProject();
        return Optional.of(projectRepository.save(project));
    }

    @Override
    public void handle(DeleteProjectCommand command) {
        if (projectRepository.existsById(command.id()))
            projectRepository.deleteById(command.id());
    }
}
