package com.bank.tsehay.wikitsehay.dto.operation;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OperationalIncidentRequest {
    private LocalDateTime incidentDate;
    private LocalDateTime resolutionDate;
    private String severity;
    private String category;
    private String description;
    private String rootCause;
    private String actionTaken;
    private Long serviceId;     // Operation reference
    private Long reportedById;  // User reference
    private Long resolvedById;  // User reference
}

