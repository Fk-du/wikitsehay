package com.bank.tsehay.wikitsehay.dto.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidentRequest {
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String severity;
    private String category;
    private Long departmentId;
    private Long projectId;
}

