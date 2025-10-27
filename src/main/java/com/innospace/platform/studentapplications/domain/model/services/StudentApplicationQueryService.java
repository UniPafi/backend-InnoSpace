package com.innospace.platform.studentapplications.domain.model.services;

import com.innospace.platform.studentapplications.domain.model.aggregates.StudentApplication;
import com.innospace.platform.studentapplications.domain.model.queries.GetStudentApplicationByIdQuery;
import com.innospace.platform.studentapplications.domain.model.queries.GetStudentApplicationsByOpportunityIdQuery;
import com.innospace.platform.studentapplications.domain.model.queries.GetStudentApplicationsByStudentIdQuery;

import java.util.List;
import java.util.Optional;

public interface StudentApplicationQueryService {
    List<StudentApplication> handle(GetStudentApplicationsByOpportunityIdQuery query);
    List<StudentApplication> handle(GetStudentApplicationsByStudentIdQuery query);
    Optional<StudentApplication> handle(GetStudentApplicationByIdQuery query);
}
