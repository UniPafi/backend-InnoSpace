package com.innospace.platform.profiles.domain.aggregates;


import com.innospace.platform.profiles.domain.commands.CreateStudentProfileCommand;
import com.innospace.platform.profiles.domain.commands.UpdateStudentProfileCommand;
import com.innospace.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "student_profiles")
@NoArgsConstructor
public class StudentProfile extends AuditableAbstractAggregateRoot<StudentProfile> {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;


    private String photoUrl;

    private String description;
    private String phoneNumber;


    @ElementCollection
    @CollectionTable(name = "student_skills", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();


    @ElementCollection
    @CollectionTable(name = "student_experiences", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "experience_entry")
    private Set<String> experiences = new HashSet<>();



    public StudentProfile(CreateStudentProfileCommand command) {
        this.userId = command.userId();
        this.name = command.name();
        this.photoUrl = command.photoUrl();
        if (command.skills() != null) this.skills.addAll(command.skills());
        if (command.experiences() != null) this.experiences.addAll(command.experiences());

        if (command.skills() != null) this.skills.addAll(command.skills());
        if (command.experiences() != null) this.experiences.addAll(command.experiences());
    }


    public void updateProfile(UpdateStudentProfileCommand cmd) {
        if (cmd.name() != null) this.name = cmd.name();
        if (cmd.photoUrl() != null) this.photoUrl = cmd.photoUrl();
        if (cmd.description() != null) this.description = cmd.description();
        if (cmd.phoneNumber() != null) this.phoneNumber = cmd.phoneNumber();

        if (cmd.skills() != null) {
            this.skills.clear();
            this.skills.addAll(cmd.skills());
        }

        if (cmd.experiences() != null) {
            this.experiences.clear();
            this.experiences.addAll(cmd.experiences());
        }
    }

    public void addSkill(String skill) { this.skills.add(skill); }
    public void removeSkill(String skill) { this.skills.remove(skill); }
    public void addExperience(String experience) { this.experiences.add(experience); }
}