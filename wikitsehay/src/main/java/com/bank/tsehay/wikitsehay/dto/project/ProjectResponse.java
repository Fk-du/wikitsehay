package com.bank.tsehay.wikitsehay.dto.project;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProjectResponse {
    private Long id;
    private String name;
    private String charter;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String department;
}

