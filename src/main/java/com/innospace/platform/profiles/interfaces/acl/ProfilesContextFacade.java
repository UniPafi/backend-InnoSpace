package com.innospace.platform.profiles.interfaces.acl;


import com.innospace.platform.iam.domain.model.valueobjects.AccountType;

public interface ProfilesContextFacade {


    Long createProfile(String name, Long userId, AccountType accountType);
}