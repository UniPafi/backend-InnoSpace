package com.innospace.platform.profiles.interfaces.rest.resources;

import java.util.Set;

public record UpdateStudentProfileResource(
        String name,
        String photoUrl,
        String description,
        String phoneNumber,
        Set<String> skills,
        Set<String> experiences
) {}
