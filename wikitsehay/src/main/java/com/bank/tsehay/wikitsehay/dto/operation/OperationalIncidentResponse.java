package com.bank.tsehay.wikitsehay.dto.operation;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OperationalIncidentResponse {
    private Long id;
    private LocalDateTime incidentDate;
    private LocalDateTime resolutionDate;
    private String severity;
    private String category;
    private String description;
    private String rootCause;
    private String actionTaken;
    private String serviceName;
    private String reportedBy;
    private String resolvedBy;
}

