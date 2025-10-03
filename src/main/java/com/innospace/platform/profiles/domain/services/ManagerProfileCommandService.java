package com.innospace.platform.profiles.domain.services;

import com.innospace.platform.profiles.domain.aggregates.ManagerProfile;
import com.innospace.platform.profiles.domain.commands.CreateManagerProfileCommand;
import com.innospace.platform.profiles.domain.commands.UpdateManagerProfileCommand;

import java.util.Optional;

public interface ManagerProfileCommandService {
    Optional<ManagerProfile> handle(CreateManagerProfileCommand command);
    Optional<ManagerProfile> handle(UpdateManagerProfileCommand command);
}