package com.innospace.platform.profiles.domain.commands;

import java.util.Set;

public record UpdateManagerProfileCommand(
        Long profileId,
        String name,
        String photoUrl,
        String companyName,
        String focusArea,
        Set<String> companyTechnologies
) {}