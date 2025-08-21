package com.bank.tsehay.wikitsehay.dto.project;

// ProgressResponse.java
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProgressResponse {
    private Long id;
    private LocalDate reportDate;
    private String summary;
    private String projectName;
}

