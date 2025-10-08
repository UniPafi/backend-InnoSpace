package com.innospace.platform.profiles.domain.commands;

import java.util.Set;

public record UpdateManagerProfileCommand(
        Long id,
        String name,
        String photoUrl,
        String description,
        String phoneNumber,
        String companyName,
        String focusArea,
        Set<String> companyTechnologies
) {}
