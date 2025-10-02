package com.innospace.platform.studentprofiles.domain.aggregates;


import com.innospace.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.innospace.platform.studentprofiles.domain.commands.CreateStudentProfileCommand;
import com.innospace.platform.studentprofiles.domain.valueobjects.EmailAddress;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class StudentProfile extends AuditableAbstractAggregateRoot<StudentProfile> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;


    @ElementCollection
    @CollectionTable(
            name = "student_skills",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "skill_name", nullable = false)
    private Set<String> skills = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "student_education",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "education_entry", nullable = false)
    private Set<String> education = new HashSet<>();

    protected StudentProfile() { }

    public StudentProfile(CreateStudentProfileCommand command) {
        this.name = command.name();
        this.email = command.email();
        if (command.skills() != null) this.skills.addAll(command.skills());
        if (command.education() != null) this.education.addAll(command.education());
    }

    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public void addEducation(String educationEntry) {
        this.education.add(educationEntry);
    }
}