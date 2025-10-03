package com.innospace.platform.profiles.domain.commands;

import java.util.Set;

public record UpdateStudentProfileCommand(
        Long profileId,
        String name,
        String photoUrl,
        Set<String> skills,
        Set<String> education,
        Set<String> experiences
) {}