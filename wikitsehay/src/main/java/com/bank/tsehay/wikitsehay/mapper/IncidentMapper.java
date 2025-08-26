package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.project.IncidentResponse;
import com.bank.tsehay.wikitsehay.model.project.Incident;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IncidentMapper {

    public IncidentResponse toResponse(Incident incident) {
        if (incident == null) return null;

        IncidentResponse response = new IncidentResponse();
        response.setId(incident.getId());
        response.setTitle(incident.getTitle());
        response.setDescription(incident.getDescription());
        response.setDateRegistered(incident.getDateRegistered());
        response.setStartDate(incident.getStartDate());
        response.setEndDate(incident.getEndDate());
        response.setSeverity(incident.getSeverity());
        response.setCategory(incident.getCategory());
        response.setDepartment(incident.getDepartment() != null ? incident.getDepartment().getName() : null);
        response.setProject(incident.getProject() != null ? incident.getProject().getName() : null);
        return response;
    }

    public List<IncidentResponse> toResponseList(List<Incident> incidents) {
        if (incidents == null) return List.of();
        return incidents.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
