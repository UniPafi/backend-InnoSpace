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


    @Column(length = 255)
    private String summary;

    @Column(length = 100)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;


    public Project(CreateProjectCommand command) {
        this.studentId = command.studentId();
        this.title = command.title();
        this.description = command.description();
        this.summary = command.summary();
        this.category = command.category();
        this.status = ProjectStatus.DRAFT;
    }


    protected Project() {}

    public void updateProject(UpdateProjectCommand command) {
        if (command.title() != null) this.title = command.title();
        if (command.description() != null) this.description = command.description();
        if (command.summary() != null) this.summary = command.summary();
        if (command.category() != null) this.category = command.category();
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