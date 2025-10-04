package com.innospace.platform.iam.domain.model.commands;

import com.innospace.platform.iam.domain.model.valueobjects.AccountType;

public record SignUpCommand(String name, String email, String password, AccountType accountType) {}