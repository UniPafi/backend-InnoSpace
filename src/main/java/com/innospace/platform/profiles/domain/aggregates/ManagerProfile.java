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

    @Column(nullable = false, unique = true)
    private String email;

    private String photoUrl;

    private String companyName;
    private String focusArea;

    @ElementCollection
    @CollectionTable(name = "manager_company_technologies", joinColumns = @JoinColumn(name = "manager_id"))
    @Column(name = "technology")
    private Set<String> companyTechnologies = new HashSet<>();

    public ManagerProfile(CreateManagerProfileCommand command) {
        this.userId = command.userId();
        this.name = command.name();
        this.email = command.email();
        this.photoUrl = command.photoUrl();
        this.companyName = command.companyName();
        this.focusArea = command.focusArea();
        if (command.companyTechnologies() != null) this.companyTechnologies.addAll(command.companyTechnologies());
    }

    public void updateProfile(UpdateManagerProfileCommand cmd) {
        this.name = cmd.name();
        this.photoUrl = cmd.photoUrl();
        this.companyName = cmd.companyName();
        this.focusArea = cmd.focusArea();
        if (cmd.companyTechnologies() != null) {
            this.companyTechnologies.clear();
            this.companyTechnologies.addAll(cmd.companyTechnologies());
        }
    }

    public void addCompanyTechnology(String tech) { this.companyTechnologies.add(tech); }
    public void removeCompanyTechnology(String tech) { this.companyTechnologies.remove(tech); }
}