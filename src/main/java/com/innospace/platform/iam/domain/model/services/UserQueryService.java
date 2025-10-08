package com.innospace.platform.iam.domain.model.services;

import com.innospace.platform.iam.domain.model.aggregates.User;
import com.innospace.platform.iam.domain.model.queries.GetAllUsersQuery;
import com.innospace.platform.iam.domain.model.queries.GetUserByEmailQuery;
import com.innospace.platform.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);


    Optional<User> handle(GetUserByIdQuery query);


    Optional<User> handle(GetUserByEmailQuery query);
}
