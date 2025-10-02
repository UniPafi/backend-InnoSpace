package com.innospace.platform.studentprofiles.interfaces.rest.resources;

import java.util.Set;

public record CreateStudentProfileResource(
        String name,
        String email,
        Set<String> skills,
        Set<String> education
) {}