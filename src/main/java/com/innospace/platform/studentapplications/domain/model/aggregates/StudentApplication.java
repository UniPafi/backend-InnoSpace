package com.innospace.platform.studentapplications.domain.model.aggregates;


import com.innospace.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.innospace.platform.studentapplications.domain.model.valueobjects.ApplicationStatus;
import com.innospace.platform.studentapplications.domain.model.valueobjects.ManagerResponseStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "student_applications", uniqueConstraints = @UniqueConstraint(columnNames = {"opportunity_id", "student_id"}))
public class StudentApplication extends AuditableAbstractAggregateRoot<StudentApplication> {

    @Column(name = "opportunity_id", nullable = false)
    private Long opportunityId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ManagerResponseStatus managerResponse;

    protected StudentApplication() {}

    public StudentApplication(Long opportunityId, Long studentId) {
        this.opportunityId = opportunityId;
        this.studentId = studentId;
        this.status = ApplicationStatus.PENDING;
        this.managerResponse = ManagerResponseStatus.PENDING;
    }

    public void accept() {
        this.managerResponse = ManagerResponseStatus.ACCEPTED;
        this.status = ApplicationStatus.ACCEPTED;
    }

    public void reject() {
        this.managerResponse = ManagerResponseStatus.REJECTED;
        this.status = ApplicationStatus.REJECTED;
    }
}
