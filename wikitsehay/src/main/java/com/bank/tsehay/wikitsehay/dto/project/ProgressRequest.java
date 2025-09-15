package com.bank.tsehay.wikitsehay.dto.project;

// ProgressRequest.java
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ProgressRequest {
    private LocalDate reportDate;
    private String summary;
    private Long projectId;
}

