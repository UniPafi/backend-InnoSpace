package com.innospace.platform.studentprofiles.domain.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
public record EmailAddress(@Email String address) {
    /**
     * Default constructor
     */
    public EmailAddress() {
        this(null);
    }
}