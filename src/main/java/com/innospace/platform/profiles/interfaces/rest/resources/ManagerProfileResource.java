package com.innospace.platform.profiles.interfaces.rest.resources;

import java.util.Set;

public record ManagerProfileResource(
        Long id,
        Long userId,
        String name,
        String photoUrl,
        String companyName,
        String focusArea,
        Set<String> companyTechnologies
) {}