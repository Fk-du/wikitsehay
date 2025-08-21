package com.bank.tsehay.wikitsehay.dto.project;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class IncidentResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dateRegistered;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String severity;
    private String category;
    private String department;
    private String project;
}

