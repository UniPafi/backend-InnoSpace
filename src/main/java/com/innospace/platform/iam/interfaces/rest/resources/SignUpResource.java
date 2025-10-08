package com.innospace.platform.iam.interfaces.rest.resources;

public record SignUpResource(
        String name,
        String email,
        String password,
        String accountType
) {}