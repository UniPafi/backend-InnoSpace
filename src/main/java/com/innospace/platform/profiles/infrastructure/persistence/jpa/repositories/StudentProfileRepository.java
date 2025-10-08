package com.innospace.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.innospace.platform.profiles.domain.aggregates.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    Optional<StudentProfile> findByUserId(Long userId);
}