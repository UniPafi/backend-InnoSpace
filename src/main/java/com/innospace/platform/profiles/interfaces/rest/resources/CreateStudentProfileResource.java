package com.innospace.platform.profiles.interfaces.rest.resources;

import java.util.List;
import java.util.Set;

public record CreateStudentProfileResource(
        Long userId,
        String name,
        String photoUrl,
        String description,
        String phoneNumber,
        Set<String> skills,
        Set<String> experiences
) {}