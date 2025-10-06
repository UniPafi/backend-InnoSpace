package com.innospace.platform.iam.interfaces.rest.transform;

import com.innospace.platform.iam.domain.model.aggregates.User;
import com.innospace.platform.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getEmail(), user.getAccountType().name());
    }
}
