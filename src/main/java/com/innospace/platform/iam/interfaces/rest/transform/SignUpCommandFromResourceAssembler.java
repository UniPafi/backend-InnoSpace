package com.innospace.platform.iam.interfaces.rest.transform;

import com.innospace.platform.iam.domain.model.commands.SignUpCommand;
import com.innospace.platform.iam.domain.model.valueobjects.AccountType;
import com.innospace.platform.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(
                resource.name(),
                resource.email(),
                resource.password(),
                AccountType.valueOf(resource.accountType().toUpperCase())
        );
    }
}
