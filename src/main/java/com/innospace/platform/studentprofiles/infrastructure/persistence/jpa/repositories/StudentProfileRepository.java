package com.innospace.platform.studentprofiles.infrastructure.persistence.jpa.repositories;

import com.innospace.platform.studentprofiles.domain.aggregates.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    /**
     * Find a Student Profile by Email
     *
     * @param email The student's email
     * @return A {@link StudentProfile} instance if found, otherwise empty
     */
    Optional<StudentProfile> findByEmail(String email);

    /**
     * Check if a Student Profile exists by Email
     *
     * @param email The student's email
     * @return true if it exists, otherwise false
     */
    boolean existsByEmail(String email);
}