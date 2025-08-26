package com.bank.tsehay.wikitsehay.dto.operation;

import lombok.Data;

// Request DTO
@Data
public class UptimeLogRequest {
    private Double uptimePercentage;
    private String periodType;
    private Long serviceId;
    private Long releaseDeploymentId;
}



