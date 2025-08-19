package com.bank.tsehay.wikitsehay.model.operation;


import com.bank.tsehay.wikitsehay.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operational_incidents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationalIncident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime incidentDate;

    private LocalDateTime resolutionDate;

    private String severity;    // e.g., LOW, MEDIUM, HIGH, CRITICAL

    private String category;    // e.g., NETWORK, HARDWARE, SOFTWARE

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String rootCause;

    @Column(length = 2000)
    private String actionTaken;

    // 🔗 Relations

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Operation service;

    @ManyToOne
    @JoinColumn(name = "reported_by_id")
    private User reportedBy;

    @ManyToOne
    @JoinColumn(name = "resolved_by_id")
    private User resolvedBy;
}

