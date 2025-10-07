package com.innospace.platform.studentprojects.domain.model.aggregates;


import com.innospace.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.innospace.platform.studentprojects.domain.model.commands.CreateProjectCommand;
import com.innospace.platform.studentprojects.domain.model.commands.UpdateProjectCommand;
import com.innospace.platform.studentprojects.domain.model.valueobjects.ProjectStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "projects")
public class Project extends AuditableAbstractAggregateRoot<Project> {

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;


    public Project(CreateProjectCommand command) {
        this.studentId = command.studentId();
        this.title = command.title();
        this.description = command.description();
        this.status = ProjectStatus.DRAFT;
    }
    public Project(Long studentId, String title, String description) {
        this.studentId = studentId;
        this.title = title;
        this.description = description;
        this.status = ProjectStatus.valueOf("DRAFT");
    }
    protected Project() {}

    public void update(UpdateProjectCommand command) {
        if (this.status == ProjectStatus.COMPLETED)
            throw new IllegalStateException("Cannot update a completed project.");

        this.title = command.title();
        this.description = command.description();
    }

    public void publish() {
        if (this.status != ProjectStatus.DRAFT)
            throw new IllegalStateException("Only draft projects can be published.");
        this.status = ProjectStatus.PUBLISHED;
    }

    public void finalizeProject() {
        if (this.status != ProjectStatus.PUBLISHED)
            throw new IllegalStateException("Only published projects can be finalized.");
        this.status = ProjectStatus.COMPLETED;
    }
}