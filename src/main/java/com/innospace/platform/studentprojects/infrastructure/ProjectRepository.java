package com.innospace.platform.studentprojects.infrastructure;

import com.innospace.platform.studentprojects.domain.model.aggregates.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByStudentId(Long studentId);

    List<Project> findAllByStatus(String status);
}