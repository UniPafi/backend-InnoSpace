package com.innospace.platform.studentprofiles.domain.commands;


import java.util.Set;

/**
 * Command for creating a new StudentProfile.
 */
public record CreateStudentProfileCommand(
        String name,
        String email,
        Set<String> skills,
        Set<String> education
) {}