package com.bank.tsehay.wikitsehay.model.operation;


import com.bank.tsehay.wikitsehay.model.Department;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "operations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private String vendor;

    private String status;

    // Service Level Agreement
    private String sla;  // could be Duration/String depending on preference

    private String criticality; // e.g., LOW, MEDIUM, HIGH

    private LocalDate startDate; // optional, useful for lifecycle tracking

    private LocalDate endDate;   // optional, useful for lifecycle tracking

    //  Relations

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department ownerDepartment;


    @Builder.Default
    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OperationalIncident> operationalIncidents = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChangeRequest> changeRequests = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReleaseDeployment> releaseDeployments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UptimeLog> uptimeLogs = new ArrayList<>();
}
