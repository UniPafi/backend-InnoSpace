package com.innospace.platform.profiles.application.acl;

import com.innospace.platform.iam.domain.model.valueobjects.AccountType;
import com.innospace.platform.profiles.domain.aggregates.ManagerProfile;
import com.innospace.platform.profiles.domain.aggregates.StudentProfile;
import com.innospace.platform.profiles.domain.commands.CreateManagerProfileCommand;
import com.innospace.platform.profiles.domain.commands.CreateStudentProfileCommand;
import com.innospace.platform.profiles.domain.services.ManagerProfileCommandService;
import com.innospace.platform.profiles.domain.services.StudentProfileCommandService;
import com.innospace.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Set;@Service
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {

    private final StudentProfileCommandService studentProfileCommandService;
    private final ManagerProfileCommandService managerProfileCommandService;

    public ProfilesContextFacadeImpl(StudentProfileCommandService studentProfileCommandService,
                                     ManagerProfileCommandService managerProfileCommandService) {
        this.studentProfileCommandService = studentProfileCommandService;
        this.managerProfileCommandService = managerProfileCommandService;
    }

    @Override
    public Long createProfile(String name, Long userId, AccountType accountType) {
        switch (accountType) {
            case STUDENT -> {
                var command = new CreateStudentProfileCommand(
                        userId,
                        name,
                        null,
                        null,
                        null,
                        Set.of(),
                        Set.of()
                );
                var profile = studentProfileCommandService.handle(command);
                return profile.map(StudentProfile::getId).orElse(0L);
            }

            case MANAGER -> {
                var command = new CreateManagerProfileCommand(
                        userId,
                        name,
                        null,
                        null,
                        null,
                        null,
                        null,
                        Set.of()
                );
                var profile = managerProfileCommandService.handle(command);
                return profile.map(ManagerProfile::getId).orElse(0L);
            }

            default -> throw new IllegalArgumentException("Unsupported account type: " + accountType);
        }
    }
}