package com.bank.tsehay.wikitsehay.dto.operation;

import lombok.Data;

import java.time.LocalDateTime;

// Request DTO
@Data
public class ReleaseDeploymentRequest {
    private String version;
    private String releaseNotes;
    private LocalDateTime deployedDate;
    private String rollbackPlan;
    private Long serviceId;
    private Long deployedById;
    private Long approvedById;
}


