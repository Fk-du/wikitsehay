package com.bank.tsehay.wikitsehay.dto.project;

import java.time.LocalDateTime;

import com.bank.tsehay.wikitsehay.Enums.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidentResponse {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime dateRegistered;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Severity severity;           // enum

    private ProjectIncidentCategory category; // enum

    private Status status;               // lifecycle status

    private String rootCause;            // optional

    private String actionTaken;          // optional

    private String department;           // department name

    private String project;              // project name
}


