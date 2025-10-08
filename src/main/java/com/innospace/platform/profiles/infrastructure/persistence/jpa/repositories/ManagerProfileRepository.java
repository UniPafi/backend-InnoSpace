package com.innospace.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.innospace.platform.profiles.domain.aggregates.ManagerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerProfileRepository extends JpaRepository<ManagerProfile, Long> {

    Optional<ManagerProfile> findByUserId(Long userId);
}