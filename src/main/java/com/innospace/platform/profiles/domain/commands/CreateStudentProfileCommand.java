package com.innospace.platform.profiles.domain.commands;

import java.util.Set;

public record CreateStudentProfileCommand(
        Long userId,
        String name,
        String photoUrl,
        String description,
        String phoneNumber,
        Set<String> skills,
        Set<String> experiences
) {}