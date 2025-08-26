package com.bank.tsehay.wikitsehay.dto.operation;

import lombok.Data;

import java.time.LocalDate;

// Request DTO
@Data
public class ReleaseNoteRequest {
    private String version;
    private String description;
    private LocalDate releaseDate;
    private Long projectId;
}

