package com.bank.tsehay.wikitsehay.dto.project;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MilestoneRequest {
    private String name;
    private LocalDate dueDate;
    private String status;
    private Long projectId;
}


