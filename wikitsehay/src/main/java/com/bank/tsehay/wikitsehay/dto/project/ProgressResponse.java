package com.bank.tsehay.wikitsehay.dto.project;

// ProgressResponse.java
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class ProgressResponse {
    private Long id;
    private LocalDate reportDate;
    private String summary;
    private String projectName;
}

