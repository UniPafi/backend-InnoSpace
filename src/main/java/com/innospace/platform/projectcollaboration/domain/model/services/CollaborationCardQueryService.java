package com.innospace.platform.projectcollaboration.domain.model.services;

import com.innospace.platform.profiles.domain.aggregates.ManagerProfile;
import com.innospace.platform.profiles.infrastructure.persistence.jpa.repositories.ManagerProfileRepository;
import com.innospace.platform.projectcollaboration.domain.model.aggregates.CollaborationRequest;
import com.innospace.platform.projectcollaboration.infrastructure.jpa.repositories.CollaborationRepository;
import com.innospace.platform.projectcollaboration.interfaces.rest.resources.CollaborationCardResource;
import com.innospace.platform.studentprojects.domain.model.aggregates.Project;
import com.innospace.platform.studentprojects.infrastructure.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CollaborationCardQueryService {

    private final CollaborationRepository collaborationRepository;
    private final ProjectRepository projectRepository;
    private final ManagerProfileRepository managerRepository;

    public CollaborationCardQueryService(
            CollaborationRepository collaborationRepository,
            ProjectRepository projectRepository,
            ManagerProfileRepository managerRepository
    ) {
        this.collaborationRepository = collaborationRepository;
        this.projectRepository = projectRepository;
        this.managerRepository = managerRepository;
    }

    public List<CollaborationCardResource> getCardsByProject(Long projectId) {
        var collaborations = collaborationRepository.findAllByProjectId(projectId);

        // Carga masiva de IDs para evitar N+1 queries
        var projectIds = collaborations.stream()
                .map(CollaborationRequest::getProjectId)
                .collect(Collectors.toSet());

        var managerIds = collaborations.stream()
                .map(CollaborationRequest::getManagerId)
                .collect(Collectors.toSet());

        var projects = projectRepository.findAllById(projectIds)
                .stream()
                .collect(Collectors.toMap(Project::getId, p -> p));

        var managers = managerRepository.findAllById(managerIds)
                .stream()
                .collect(Collectors.toMap(ManagerProfile::getId, m -> m));

        return collaborations.stream()
                .map(c -> {
                    var project = projects.get(c.getProjectId());
                    var manager = managers.get(c.getManagerId());
                    if (project == null || manager == null) return null;

                    return new CollaborationCardResource(
                            c.getId(),
                            project.getId(),
                            project.getTitle(),
                            project.getDescription(),
                            manager.getId(),
                            manager.getName(),
                            manager.getCompanyName(),
                            manager.getPhotoUrl(),
                            c.getStudentResponse().name()
                    );
                })
                .filter(Objects::nonNull)
                .toList();
    }
}