package com.bank.tsehay.wikitsehay.dto.project;

import lombok.Data;

import java.time.LocalDateTime;

import com.bank.tsehay.wikitsehay.Enums.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidentRequest {

    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Severity severity;          // updated to enum

    private ProjectIncidentCategory category;  // updated to enum

    private Status status;              // new field for lifecycle tracking

    private String rootCause;           // optional post-mortem

    private String actionTaken;         // optional post-mortem

    private Long departmentId;

    private Long projectId;
}


