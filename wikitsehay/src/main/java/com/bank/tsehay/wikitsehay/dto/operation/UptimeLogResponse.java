package com.bank.tsehay.wikitsehay.dto.operation;

import lombok.Data;

import java.time.LocalDateTime;

// Response DTO
@Data
public class UptimeLogResponse {
    private Long id;
    private LocalDateTime logDate;
    private Double uptimePercentage;
    private String periodType;
    private Long serviceId;
    private Long releaseDeploymentId;
}
