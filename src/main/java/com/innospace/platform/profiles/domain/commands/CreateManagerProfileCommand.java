package com.innospace.platform.profiles.domain.commands;

import java.util.Set;

public record CreateManagerProfileCommand(
        Long userId,
        String name,
        String email,
        String photoUrl,
        String companyName,
        String focusArea,
        Set<String> companyTechnologies
) {}