package com.innospace.platform.iam.application.internal.commandservices;

import com.innospace.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.innospace.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.innospace.platform.iam.domain.model.aggregates.User;
import com.innospace.platform.iam.domain.model.commands.SignInCommand;
import com.innospace.platform.iam.domain.model.commands.SignUpCommand;
import com.innospace.platform.iam.domain.model.commands.UpdateUserCommand;
import com.innospace.platform.iam.domain.model.services.UserCommandService;
import com.innospace.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.innospace.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final ProfilesContextFacade profilesContextFacade;

    public UserCommandServiceImpl(
            UserRepository userRepository,
            HashingService hashingService,
            TokenService tokenService,
            ProfilesContextFacade profilesContextFacade) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.profilesContextFacade = profilesContextFacade;
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty())
            throw new RuntimeException("User not found");

        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");

        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email()))
            throw new RuntimeException("User already exists with email " + command.email());

        var encodedPassword = hashingService.encode(command.password());
        var user = new User(command.email(), encodedPassword, command.accountType());
        userRepository.save(user);

        profilesContextFacade.createProfile(command.name(), user.getId(), command.accountType());

        return Optional.of(user);
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        var user = userRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.updateEmail(command.email());
        return Optional.of(userRepository.save(user));
    }
}