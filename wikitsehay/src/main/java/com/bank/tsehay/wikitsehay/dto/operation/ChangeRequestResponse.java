package com.bank.tsehay.wikitsehay.dto.operation;

import lombok.Data;

import java.time.LocalDateTime;

// Response DTO
@Data
public class ChangeRequestResponse {
    private Long id;
    private LocalDateTime requestDate;
    private String type;
    private String approvalStatus;
    private LocalDateTime implementationDate;
    private Long serviceId;
    private Long requesterId;
    private Long approvedById;
    private Long implementedById;
}
