package com.innospace.platform.studentapplications.infrastructure.jpa.repositories;

import com.innospace.platform.projectcollaboration.domain.model.aggregates.CollaborationRequest;
import com.innospace.platform.studentapplications.domain.model.aggregates.StudentApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentApplicationRepository extends JpaRepository<StudentApplication, Long> {
    List<StudentApplication> findAllByOpportunityId(Long opportunityId);

    List<StudentApplication> findAllByStudentId(Long studentId);
}