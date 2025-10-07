package com.innospace.platform.companyopportunities.infrastructure.persistence.jpa.repositories;

import com.innospace.platform.companyopportunities.domain.model.aggregates.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
    List<Opportunity> findAllByCompanyId(Long companyId);
}