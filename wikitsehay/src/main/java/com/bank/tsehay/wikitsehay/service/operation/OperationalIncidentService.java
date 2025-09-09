package com.bank.tsehay.wikitsehay.service.operation;

import com.bank.tsehay.wikitsehay.dto.operation.OperationalIncidentRequest;
import com.bank.tsehay.wikitsehay.dto.operation.OperationalIncidentResponse;
import com.bank.tsehay.wikitsehay.model.operation.Operation;
import com.bank.tsehay.wikitsehay.model.operation.OperationalIncident;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.repository.UserRepository;
import com.bank.tsehay.wikitsehay.repository.operation.OperationRepository;
import com.bank.tsehay.wikitsehay.repository.operation.OperationalIncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class OperationalIncidentService {

    private final OperationalIncidentRepository incidentRepository;
    private final OperationRepository operationRepository;
    private final UserRepository userRepository;

    public OperationalIncidentResponse createIncident(OperationalIncidentRequest request) {
        Operation operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new RuntimeException("Operation not found"));

        User reportedBy = (request.getReportedById() != null)
                ? userRepository.findById(request.getReportedById())
                .orElseThrow(() -> new RuntimeException("ReportedBy user not found"))
                : null;

        User resolvedBy = (request.getResolvedById() != null)
                ? userRepository.findById(request.getResolvedById())
                .orElseThrow(() -> new RuntimeException("ResolvedBy user not found"))
                : null;

        OperationalIncident incident = OperationalIncident.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .severity(request.getSeverity())
                .status(request.getStatus())
                .incidentDate(request.getIncidentDate())
                .resolutionDate(request.getResolutionDate())
                .category(request.getCategory())
                .rootCause(request.getRootCause())
                .actionTaken(request.getActionTaken())
                .operation(operation)
                .reportedBy(reportedBy)
                .resolvedBy(resolvedBy)
                .build();

        return toResponse(incidentRepository.save(incident));
    }

    public List<OperationalIncidentResponse> getAllIncidents() {
        return incidentRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public OperationalIncidentResponse getIncidentById(Long id) {
        return incidentRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Incident not found"));
    }

    public OperationalIncidentResponse updateIncident(Long id, OperationalIncidentRequest request) {
        OperationalIncident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident not found"));

        Operation operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new RuntimeException("Operation not found"));

        User reportedBy = (request.getReportedById() != null)
                ? userRepository.findById(request.getReportedById())
                .orElseThrow(() -> new RuntimeException("ReportedBy user not found"))
                : null;

        User resolvedBy = (request.getResolvedById() != null)
                ? userRepository.findById(request.getResolvedById())
                .orElseThrow(() -> new RuntimeException("ResolvedBy user not found"))
                : null;

        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setSeverity(request.getSeverity());
        incident.setStatus(request.getStatus());
        incident.setIncidentDate(request.getIncidentDate());
        incident.setResolutionDate(request.getResolutionDate());
        incident.setCategory(request.getCategory());
        incident.setRootCause(request.getRootCause());
        incident.setActionTaken(request.getActionTaken());
        incident.setOperation(operation);
        incident.setReportedBy(reportedBy);
        incident.setResolvedBy(resolvedBy);

        return toResponse(incidentRepository.save(incident));
    }

    public void deleteIncident(Long id) {
        incidentRepository.deleteById(id);
    }

    private OperationalIncidentResponse toResponse(OperationalIncident incident) {
        OperationalIncidentResponse response = new OperationalIncidentResponse();
        response.setId(incident.getId());
        response.setIncidentDate(incident.getIncidentDate());
        response.setResolutionDate(incident.getResolutionDate());
        response.setSeverity(incident.getSeverity());
        response.setCategory(incident.getCategory());
        response.setDescription(incident.getDescription());
        response.setRootCause(incident.getRootCause());
        response.setActionTaken(incident.getActionTaken());
        response.setServiceName(incident.getOperation().getName());
        response.setReportedBy(incident.getReportedBy() != null
                ? incident.getReportedBy().getFirstName() + " " + incident.getReportedBy().getLastName()
                : null);
        response.setResolvedBy(incident.getResolvedBy() != null
                ? incident.getResolvedBy().getFirstName() + " " + incident.getResolvedBy().getLastName()
                : null);
        return response;
    }
}
