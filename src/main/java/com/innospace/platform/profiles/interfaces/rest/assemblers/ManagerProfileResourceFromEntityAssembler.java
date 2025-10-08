package com.innospace.platform.profiles.interfaces.rest.assemblers;

import com.innospace.platform.profiles.domain.aggregates.ManagerProfile;
import com.innospace.platform.profiles.interfaces.rest.resources.ManagerProfileResource;

public class ManagerProfileResourceFromEntityAssembler {
    public static ManagerProfileResource toResourceFromEntity(ManagerProfile entity) {
        return new ManagerProfileResource(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getPhotoUrl(),
                entity.getDescription(),
                entity.getPhoneNumber(),
                entity.getCompanyName(),
                entity.getFocusArea(),
                entity.getCompanyTechnologies()
        );
    }
}