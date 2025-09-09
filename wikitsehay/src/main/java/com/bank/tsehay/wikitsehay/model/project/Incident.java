package com.bank.tsehay.wikitsehay.model.project;

import com.bank.tsehay.wikitsehay.model.BaseIncident;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.Enums.ProjectIncidentCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "incidents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Incident extends BaseIncident {

    private LocalDateTime dateRegistered;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private ProjectIncidentCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = true)
    private Project project;

    // Optional: rootCause / actionTaken for post-mortem
    @Column(length = 2000)
    private String rootCause;

    @Column(length = 2000)
    private String actionTaken;
}
