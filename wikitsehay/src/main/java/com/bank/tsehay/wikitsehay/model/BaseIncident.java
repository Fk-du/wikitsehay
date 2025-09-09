package com.bank.tsehay.wikitsehay.model;

import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.Enums.Severity;
import com.bank.tsehay.wikitsehay.Enums.Status;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseIncident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = true)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_id", nullable = true)
    private User updatedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
