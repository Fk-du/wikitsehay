package com.bank.tsehay.wikitsehay.dto.operation;


import lombok.Data;
import java.time.LocalDateTime;


import com.bank.tsehay.wikitsehay.Enums.OperationalIncidentCategory;
import com.bank.tsehay.wikitsehay.Enums.Severity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationalIncidentResponse {

    private Long id;

    private LocalDateTime incidentDate;

    private LocalDateTime resolutionDate;

    private Severity severity;                 // use enum

    private OperationalIncidentCategory category;  // use enum

    private String description;

    private String rootCause;

    private String actionTaken;

    private String serviceName;                // operation/service name

    private String reportedBy;                 // user full name or email

    private String resolvedBy;                 // user full name or email
}

