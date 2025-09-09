package com.bank.tsehay.wikitsehay.model.operation;

import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.model.BaseIncident;
import com.bank.tsehay.wikitsehay.Enums.OperationalIncidentCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "operational_incidents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OperationalIncident extends BaseIncident {

    private LocalDateTime incidentDate;
    private LocalDateTime resolutionDate;

    @Enumerated(EnumType.STRING)
    private OperationalIncidentCategory category;

    @Column(length = 2000)
    private String rootCause;

    @Column(length = 2000)
    private String actionTaken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Operation operation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_by_id", nullable = true)
    private User reportedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolved_by_id", nullable = true)
    private User resolvedBy;
}
