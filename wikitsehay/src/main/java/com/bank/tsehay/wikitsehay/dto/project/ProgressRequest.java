package com.bank.tsehay.wikitsehay.dto.project;

// ProgressRequest.java
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProgressRequest {
    private LocalDate reportDate;
    private String summary;
    private Long projectId;
}

