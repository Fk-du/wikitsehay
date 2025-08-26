package com.bank.tsehay.wikitsehay.dto.operation;


import lombok.Data;

import java.time.LocalDateTime;

// Response DTO
@Data
public class ReleaseDeploymentResponse {
    private Long id;
    private String version;
    private String releaseNotes;
    private LocalDateTime deployedDate;
    private String rollbackPlan;
    private Long serviceId;
    private Long deployedById;
    private Long approvedById;
}
