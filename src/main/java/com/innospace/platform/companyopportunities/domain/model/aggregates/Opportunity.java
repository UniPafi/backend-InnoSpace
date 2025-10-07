package com.innospace.platform.companyopportunities.domain.model.aggregates;


import com.innospace.platform.companyopportunities.domain.model.commands.CreateOpportunityCommand;
import com.innospace.platform.companyopportunities.domain.model.commands.UpdateOpportunityCommand;
import com.innospace.platform.companyopportunities.domain.model.valueobjects.OpportunityStatus;
import com.innospace.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;


@Getter
@Entity
@Table(name = "opportunities")
public class Opportunity extends AuditableAbstractAggregateRoot<Opportunity> {

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "opportunity_requirements", joinColumns = @JoinColumn(name = "opportunity_id"))
    @Column(name = "requirement")
    private List<String> requirements;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OpportunityStatus status;



    public Opportunity(CreateOpportunityCommand command) {
        this.companyId = command.companyId();
        this.title = command.title();
        this.description = command.description();
        this.requirements = command.requirements();
        this.status = OpportunityStatus.DRAFT;
    }

    protected Opportunity() {}



    public void update(UpdateOpportunityCommand command) {
        if (this.status == OpportunityStatus.CLOSED)
            throw new IllegalStateException("Cannot update a closed opportunity.");

        this.title = command.title();
        this.description = command.description();
        this.requirements = command.requirements();
    }

    public void publish() {
        if (this.status != OpportunityStatus.DRAFT)
            throw new IllegalStateException("Only draft opportunities can be published.");
        this.status = OpportunityStatus.PUBLISHED;
    }

    public void close() {
        if (this.status != OpportunityStatus.PUBLISHED)
            throw new IllegalStateException("Only published opportunities can be closed.");
        this.status = OpportunityStatus.CLOSED;
    }
}