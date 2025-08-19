package com.bank.tsehay.wikitsehay.model.operation;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "uptime_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UptimeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime logDate;

    private Double uptimePercentage;

    private String periodType; // e.g., DAILY, WEEKLY, MONTHLY, QUARTERLY

    // 🔗 Relations

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Operation service;

    @OneToOne
    @JoinColumn(name = "release_deployment_id")
    private ReleaseDeployment releaseDeployment;
}
