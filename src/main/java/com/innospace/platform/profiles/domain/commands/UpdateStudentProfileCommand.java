package com.innospace.platform.profiles.domain.commands;

import java.util.Set;

public record UpdateStudentProfileCommand(
        Long id,
        String name,
        String photoUrl,
        String description,
        String phoneNumber,
        String portfolioUrl,
        Set<String> skills,
        Set<String> experiences
) {}
