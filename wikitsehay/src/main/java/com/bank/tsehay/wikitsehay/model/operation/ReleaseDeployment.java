package com.bank.tsehay.wikitsehay.model.operation;


import com.bank.tsehay.wikitsehay.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "release_deployments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReleaseDeployment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String version;

    @Column(length = 2000)
    private String releaseNotes;

    private LocalDateTime deployedDate;

    @Column(length = 2000)
    private String rollbackPlan;

    // 🔗 Relations

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Operation service;

    @ManyToOne
    @JoinColumn(name = "deployed_by_id")
    private User deployedBy;

    @ManyToOne
    @JoinColumn(name = "approved_by_id")
    private User approvedBy;

    @OneToOne(mappedBy = "releaseDeployment", cascade = CascadeType.ALL, orphanRemoval = true)
    private UptimeLog uptimeLog;
}
