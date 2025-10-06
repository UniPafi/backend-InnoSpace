package com.innospace.platform.profiles.domain.commands;

import java.util.Set;

public record CreateStudentProfileCommand(
        Long userId,
        String name,
        String photoUrl,
        Set<String> skills,
        Set<String> education,
        Set<String> experiences
) {}