package com.bank.tsehay.wikitsehay.dto.operation;

import lombok.Data;

import java.time.LocalDateTime;

// Request DTO
@Data
public class ChangeRequestRequest {
    private String type;
    private String approvalStatus;
    private LocalDateTime implementationDate;
    private Long serviceId;
    private Long requesterId;
    private Long approvedById;
    private Long implementedById;
}



