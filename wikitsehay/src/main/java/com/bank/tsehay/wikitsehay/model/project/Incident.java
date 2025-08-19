package com.bank.tsehay.wikitsehay.model.project;


import com.bank.tsehay.wikitsehay.model.Department;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private LocalDateTime dateRegistered;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String severity; // e.g., Low, Medium, High, Critical

    private String category; // e.g., System Failure, CyberAttack, Hardware Failure, ThirdParty Issue

    // Incident can be linked to a department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // Incident can be linked to a project
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}
