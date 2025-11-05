package com.innospace.platform.projectcollaboration.infrastructure.jpa.repositories;

import com.innospace.platform.projectcollaboration.domain.model.aggregates.CollaborationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaborationRepository extends JpaRepository<CollaborationRequest, Long> {
    List<CollaborationRequest> findAllByProjectId(Long projectId);


    List<CollaborationRequest> findAllByManagerId(Long managerId);

}