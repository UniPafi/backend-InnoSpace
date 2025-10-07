package com.innospace.platform.studentprojects.application.internal.queryservices;


import com.innospace.platform.studentprojects.domain.model.aggregates.Project;
import com.innospace.platform.studentprojects.domain.model.queries.GetAllStudentProjectsQuery;
import com.innospace.platform.studentprojects.domain.model.queries.GetProjectByIdQuery;
import com.innospace.platform.studentprojects.domain.model.queries.ValidateProjectOwnershipQuery;
import com.innospace.platform.studentprojects.domain.services.ProjectQueryService;
import com.innospace.platform.studentprojects.infrastructure.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectQueryServiceImpl implements ProjectQueryService {

    private final ProjectRepository projectRepository;

    public ProjectQueryServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> handle(GetProjectByIdQuery query) {
        return projectRepository.findById(query.projectId());
    }

    @Override
    public List<Project> handle(GetAllStudentProjectsQuery query) {
        return projectRepository.findAllByStudentId(query.studentId());
    }

    @Override
    public boolean handle(ValidateProjectOwnershipQuery query) {
        var project = projectRepository.findById(query.projectId());
        return project.map(p -> p.getStudentId().equals(query.studentId())).orElse(false);
    }
}