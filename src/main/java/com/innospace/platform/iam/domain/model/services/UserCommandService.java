package com.innospace.platform.iam.domain.model.services;

import com.innospace.platform.iam.domain.model.aggregates.User;
import com.innospace.platform.iam.domain.model.commands.SignInCommand;
import com.innospace.platform.iam.domain.model.commands.SignUpCommand;
import com.innospace.platform.iam.domain.model.commands.UpdateUserCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    Optional<User> handle(SignUpCommand command);
    Optional<User> handle(UpdateUserCommand command);
}
