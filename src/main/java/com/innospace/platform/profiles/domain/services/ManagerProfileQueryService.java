package com.innospace.platform.profiles.domain.services;

import com.innospace.platform.profiles.domain.aggregates.ManagerProfile;
import com.innospace.platform.profiles.domain.queries.GetAllManagerProfilesQuery;
import com.innospace.platform.profiles.domain.queries.GetManagerProfileByEmailQuery;
import com.innospace.platform.profiles.domain.queries.GetManagerProfileByIdQuery;
import com.innospace.platform.profiles.domain.queries.GetManagerProfileByUserIdQuery;

import java.util.List;
import java.util.Optional;


public interface ManagerProfileQueryService {
    Optional<ManagerProfile> handle(GetManagerProfileByIdQuery query);
    Optional<ManagerProfile> handle(GetManagerProfileByUserIdQuery query);
    Optional<ManagerProfile> handle(GetManagerProfileByEmailQuery query);
    List<ManagerProfile> handle(GetAllManagerProfilesQuery query);
}
