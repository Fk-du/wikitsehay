package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.project.ProjectResponse;
import com.bank.tsehay.wikitsehay.model.project.Project;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    // Map Project entity to ProjectResponse DTO
    public ProjectResponse toResponse(Project project) {
        if (project == null) return null;

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .charter(project.getCharter())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .status(project.getStatus())
                .department(project.getDepartment() != null ? project.getDepartment().getName() : null)
                .build();
    }

    // Optional: Map List<Project> to List<ProjectResponse>
    public List<ProjectResponse> toResponseList(List<Project> projects) {
        if (projects == null) return List.of();
        return projects.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
