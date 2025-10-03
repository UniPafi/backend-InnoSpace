package com.innospace.platform.profiles.interfaces.rest.resources;

import java.util.List;
import java.util.Set;

public record StudentProfileResource(
        Long id,
        Long userId,
        String name,
        String photoUrl,
        Set<String> skills,
        Set<String> education,
        Set<String> experiences
) {}