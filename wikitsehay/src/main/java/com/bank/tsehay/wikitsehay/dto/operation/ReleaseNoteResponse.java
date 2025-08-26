package com.bank.tsehay.wikitsehay.dto.operation;

import lombok.Data;

import java.time.LocalDate;

// Response DTO
@Data
public class ReleaseNoteResponse {
    private Long id;
    private String version;
    private String description;
    private LocalDate releaseDate;
    private Long projectId;
}
