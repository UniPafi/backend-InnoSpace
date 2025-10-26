package com.innospace.platform.projectcollaboration.domain.model.aggregates;

import com.innospace.platform.projectcollaboration.domain.model.valueobjects.CollaborationStatus;
import com.innospace.platform.projectcollaboration.domain.model.valueobjects.StudentResponseStatus;
import com.innospace.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "collaboration_requests", uniqueConstraints = @UniqueConstraint(columnNames = {"project_id", "manager_id"}))
public class CollaborationRequest extends AuditableAbstractAggregateRoot<CollaborationRequest> {



    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "manager_id", nullable = false)
    private Long managerId;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CollaborationStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentResponseStatus studentResponse;

    protected CollaborationRequest() {}

    public CollaborationRequest(Long projectId, Long managerId) {
        this.projectId = projectId;
        this.managerId = managerId;
        this.status = CollaborationStatus.PENDING;
        this.studentResponse = StudentResponseStatus.PENDING;
    }

    public void accept() {
        this.studentResponse = StudentResponseStatus.ACCEPTED;
        this.status = CollaborationStatus.CONFIRMED;
    }

    public void reject() {
        this.studentResponse = StudentResponseStatus.REJECTED;
        this.status = CollaborationStatus.REJECTED;
    }
}