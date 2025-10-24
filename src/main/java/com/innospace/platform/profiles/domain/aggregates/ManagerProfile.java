package com.innospace.platform.profiles.domain.aggregates;


import com.innospace.platform.profiles.domain.commands.CreateManagerProfileCommand;
import com.innospace.platform.profiles.domain.commands.UpdateManagerProfileCommand;
import com.innospace.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "manager_profiles")
@NoArgsConstructor
public class ManagerProfile extends AuditableAbstractAggregateRoot<ManagerProfile> {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(name = "photo_url", columnDefinition = "LONGTEXT")
    private String photoUrl;
    private String location;
    private String description;
    private String phoneNumber;

    private String companyName;
    private String focusArea;

    @ElementCollection
    @CollectionTable(name = "manager_company_technologies", joinColumns = @JoinColumn(name = "manager_id"))
    @Column(name = "technology")
    private Set<String> companyTechnologies = new HashSet<>();

    public ManagerProfile(CreateManagerProfileCommand command) {
        this.userId = command.userId();
        this.name = command.name();
        this.photoUrl = command.photoUrl();
        this.description = command.description();
        this.phoneNumber = command.phoneNumber();
        this.companyName = command.companyName();
        this.focusArea = command.focusArea();
        this.location = command.location();
        if (command.companyTechnologies() != null) this.companyTechnologies.addAll(command.companyTechnologies());
    }


    public void updateProfile(UpdateManagerProfileCommand cmd) {
        this.name = cmd.name();
        this.photoUrl = cmd.photoUrl();
        this.description = cmd.description();
        this.phoneNumber = cmd.phoneNumber();
        this.companyName = cmd.companyName();
        this.focusArea = cmd.focusArea();
        if (cmd.location() != null) this.location = cmd.location();
        if (cmd.companyTechnologies() != null) {
            this.companyTechnologies.clear();
            this.companyTechnologies.addAll(cmd.companyTechnologies());
        }
    }

    public void addCompanyTechnology(String tech) { this.companyTechnologies.add(tech); }
    public void removeCompanyTechnology(String tech) { this.companyTechnologies.remove(tech); }
}