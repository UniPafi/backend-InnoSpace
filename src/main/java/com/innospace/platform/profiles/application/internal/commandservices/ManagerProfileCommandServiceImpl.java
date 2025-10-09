package com.innospace.platform.profiles.application.internal.commandservices;

import com.innospace.platform.profiles.domain.aggregates.ManagerProfile;
import com.innospace.platform.profiles.domain.commands.CreateManagerProfileCommand;
import com.innospace.platform.profiles.domain.commands.UpdateManagerProfileCommand;
import com.innospace.platform.profiles.domain.services.ManagerProfileCommandService;
import com.innospace.platform.profiles.infrastructure.persistence.jpa.repositories.ManagerProfileRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ManagerProfileCommandServiceImpl implements ManagerProfileCommandService {

    private final ManagerProfileRepository managerProfileRepository;

    public ManagerProfileCommandServiceImpl(ManagerProfileRepository managerProfileRepository) {
        this.managerProfileRepository = managerProfileRepository;
    }

    @Override
    public Optional<ManagerProfile> handle(CreateManagerProfileCommand command) {
        var profile = new ManagerProfile(command);
        return Optional.of(managerProfileRepository.save(profile));
    }

    @Override
    public Optional<ManagerProfile> handle(UpdateManagerProfileCommand command) {
        var existing = managerProfileRepository.findById(command.id());
        if (existing.isEmpty()) return Optional.empty();

        var profile = existing.get();
        profile.updateProfile(command);
        return Optional.of(managerProfileRepository.save(profile));
    }

}