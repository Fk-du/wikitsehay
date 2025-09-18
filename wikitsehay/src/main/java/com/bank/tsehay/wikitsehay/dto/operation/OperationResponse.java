package com.bank.tsehay.wikitsehay.dto.operation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {
    private Long id;
    private String name;
    private String description;
    private String vendor;
    private String status;
    private String sla;
    private String criticality;
    private LocalDate startDate;
    private LocalDate endDate;
    private String departmentName;
}

