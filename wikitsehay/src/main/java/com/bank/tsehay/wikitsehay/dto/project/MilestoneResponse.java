package com.bank.tsehay.wikitsehay.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MilestoneResponse {
    private Long id;
    private String name;
    private LocalDate dueDate;
    private String status;
    private Long projectId;
}
