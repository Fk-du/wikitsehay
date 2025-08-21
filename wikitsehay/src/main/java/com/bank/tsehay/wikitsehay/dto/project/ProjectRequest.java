package com.bank.tsehay.wikitsehay.dto.project;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectRequest {
    private String name;
    private String charter;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Long departmentId;
}
