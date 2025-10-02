package com.innospace.platform.studentprofiles.interfaces.rest.resources;

import java.util.Set;

public record StudentProfileResource(
        Long id,
        String name,
        String email,
        Set<String> skills,
        Set<String> education
) {}