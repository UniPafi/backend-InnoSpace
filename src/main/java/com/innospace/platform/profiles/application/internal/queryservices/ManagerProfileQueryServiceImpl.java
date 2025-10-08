package com.innospace.platform.profiles.application.internal.queryservices;


import com.innospace.platform.profiles.domain.aggregates.ManagerProfile;
import com.innospace.platform.profiles.domain.queries.GetAllManagerProfilesQuery;
import com.innospace.platform.profiles.domain.queries.GetManagerProfileByIdQuery;
import com.innospace.platform.profiles.domain.queries.GetManagerProfileByUserIdQuery;
import com.innospace.platform.profiles.domain.services.ManagerProfileQueryService;
import com.innospace.platform.profiles.infrastructure.persistence.jpa.repositories.ManagerProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerProfileQueryServiceImpl implements ManagerProfileQueryService {

    private final ManagerProfileRepository managerProfileRepository;

    public ManagerProfileQueryServiceImpl(ManagerProfileRepository managerProfileRepository) {
        this.managerProfileRepository = managerProfileRepository;
    }

    @Override
    public Optional<ManagerProfile> handle(GetManagerProfileByIdQuery query) {
        return managerProfileRepository.findById(query.profileId());
    }



    @Override
    public List<ManagerProfile> handle(GetAllManagerProfilesQuery query) {
        return managerProfileRepository.findAll();
    }
    @Override
    public Optional<ManagerProfile> handle(GetManagerProfileByUserIdQuery query) {
        return managerProfileRepository.findByUserId(query.userId());
    }

}