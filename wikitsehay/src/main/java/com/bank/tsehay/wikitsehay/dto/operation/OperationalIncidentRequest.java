package com.bank.tsehay.wikitsehay.dto.operation;

import java.time.LocalDateTime;



import com.bank.tsehay.wikitsehay.Enums.OperationalIncidentCategory;
import com.bank.tsehay.wikitsehay.Enums.Severity;
import com.bank.tsehay.wikitsehay.Enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationalIncidentRequest {
    private String title;
    private String description;
    private Severity severity;
    private Status status;

    private LocalDateTime incidentDate;
    private LocalDateTime resolutionDate;
    private OperationalIncidentCategory category;
    private String rootCause;
    private String actionTaken;
    private Long operationId;
    private Long reportedById;
    private Long resolvedById;
}
