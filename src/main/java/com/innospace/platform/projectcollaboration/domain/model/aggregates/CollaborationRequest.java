package com.innospace.platform.projectcollaboration.domain.model.aggregates;

import com.innospace.platform.projectcollaboration.domain.model.valueobjects.StudentResponseStatus;
import com.innospace.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "collaboration_requests",
        uniqueConstraints = @UniqueConstraint(columnNames = {"project_id", "manager_id"}))
@NoArgsConstructor
public class CollaborationRequest extends AuditableAbstractAggregateRoot<CollaborationRequest> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "manager_id", nullable = false)
    private Long managerId;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentResponseStatus studentResponse;

    public CollaborationRequest(Long projectId, Long managerId) {
        this.projectId = projectId;
        this.managerId = managerId;
        this.studentResponse = StudentResponseStatus.PENDING;
    }


    public void respond(StudentResponseStatus response) {
        if (this.studentResponse != StudentResponseStatus.PENDING)
            throw new IllegalStateException("Student already responded.");
        this.studentResponse = response;
    }
}