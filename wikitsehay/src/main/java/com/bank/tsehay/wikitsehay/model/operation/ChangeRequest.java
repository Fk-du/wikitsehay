package com.bank.tsehay.wikitsehay.model.operation;


import com.bank.tsehay.wikitsehay.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "change_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime requestDate;

    private String type; // e.g., BUG_FIX, ENHANCEMENT, UPGRADE

    private String approvalStatus; // e.g., PENDING, APPROVED, REJECTED

    private LocalDateTime implementationDate;

    // 🔗 Relations

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Operation service;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;

    @ManyToOne
    @JoinColumn(name = "approved_by_id")
    private User approvedBy;

    @ManyToOne
    @JoinColumn(name = "implemented_by_id")
    private User implementedBy;
}

