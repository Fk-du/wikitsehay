package com.bank.tsehay.wikitsehay.model.project;

import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.operation.ReleaseNote;
import com.bank.tsehay.wikitsehay.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;


@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String charter;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private String vendor;

    // 🔗 Relations
    @ManyToOne
    @JoinColumn(name = "manager_id") // FK to users table
    private User manager;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Milestone> milestones = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReleaseNote> releaseNotes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectFile> projectFiles = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Progress> progressEntries = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incident> incidents = new ArrayList<>();
}
