package com.bank.tsehay.wikitsehay.dto.operation;


import lombok.Data;
import java.time.LocalDate;

@Data
public class OperationRequest {
    private String name;
    private String description;
    private String vendor;
    private String status;
    private String sla;
    private String criticality;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long departmentId; // reference to Department
}

