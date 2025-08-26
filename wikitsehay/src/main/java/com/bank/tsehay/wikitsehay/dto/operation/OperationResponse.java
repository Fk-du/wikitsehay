package com.bank.tsehay.wikitsehay.dto.operation;


import lombok.Data;
import java.time.LocalDate;

@Data
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

