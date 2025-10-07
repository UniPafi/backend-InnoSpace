package com.innospace.platform.studentprojects.domain.services;

import com.innospace.platform.studentprojects.domain.model.aggregates.Project;
import com.innospace.platform.studentprojects.domain.model.queries.GetAllStudentProjectsQuery;
import com.innospace.platform.studentprojects.domain.model.queries.GetProjectByIdQuery;
import com.innospace.platform.studentprojects.domain.model.queries.ValidateProjectOwnershipQuery;

import java.util.List;
import java.util.Optional;

public interface ProjectQueryService {

    Optional<Project> handle(GetProjectByIdQuery query);

    List<Project> handle(GetAllStudentProjectsQuery query);

    boolean handle(ValidateProjectOwnershipQuery query);
}