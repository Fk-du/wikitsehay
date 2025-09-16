package com.bank.tsehay.wikitsehay.service.project;

import com.bank.tsehay.wikitsehay.dto.operation.OperationalIncidentResponse;
import com.bank.tsehay.wikitsehay.dto.project.IncidentRequest;
import com.bank.tsehay.wikitsehay.dto.project.IncidentResponse;
import com.bank.tsehay.wikitsehay.exceptions.ResourceNotFoundException;
import com.bank.tsehay.wikitsehay.mapper.IncidentMapper;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.project.Incident;
import com.bank.tsehay.wikitsehay.model.project.Project;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.repository.DepartmentRepository;
import com.bank.tsehay.wikitsehay.repository.project.IncidentRepository;
import com.bank.tsehay.wikitsehay.repository.project.ProjectRepository;
import com.bank.tsehay.wikitsehay.service.user.AuthService;
import com.bank.tsehay.wikitsehay.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final IncidentMapper incidentMapper;
    private final AuthService authService;


    private User getCurrentUser() {
        return authService.getCurrentUser();
    }

    private void checkDepartmentAccess(Department department) {
        User user = getCurrentUser();
        if (user.getRole().getName().equals("SUPER_USER")) return;
        if (!user.getDepartment().getId().equals(department.getId())) {
            throw new RuntimeException("Access denied: not your department");
        }
    }

    public IncidentResponse createIncident(IncidentRequest request) {
        Department dept = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        checkDepartmentAccess(dept);

        Project proj = null;
        if (request.getProjectId() != null) {
            proj = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
        }

        Incident incident = Incident.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dateRegistered(LocalDateTime.now())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .severity(request.getSeverity())
                .category(request.getCategory())
                .status(request.getStatus() != null ? request.getStatus() : com.bank.tsehay.wikitsehay.Enums.Status.OPEN)
                .rootCause(request.getRootCause())
                .actionTaken(request.getActionTaken())
                .department(dept)
                .project(proj)
                .createdBy(getCurrentUser())
                .build();

        Incident saved = incidentRepository.save(incident);
        return incidentMapper.toResponse(saved);
    }

    public List<IncidentResponse> getAllIncidents() {
        return incidentRepository.findAll().stream()
                .map(incidentMapper::toResponse)  // map each Incident to IncidentResponse
                .collect(Collectors.toList());
    }

    public IncidentResponse getIncident(Long id) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident not found"));

        checkDepartmentAccess(incident.getDepartment());
        return incidentMapper.toResponse(incident);
    }

    public List<IncidentResponse> getByDepartment(Long departmentId) {
        return incidentRepository.findByDepartmentId(departmentId)
                .stream().map(incidentMapper::toResponse).toList();
    }

    public List<IncidentResponse> getByProject(Long projectId) {
        return incidentRepository.findByProjectId(projectId)
                .stream().map(incidentMapper::toResponse).toList();
    }

    public IncidentResponse updateIncident(Long id, IncidentRequest request) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident not found"));

        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setStartDate(request.getStartDate());
        incident.setEndDate(request.getEndDate());
        incident.setSeverity(request.getSeverity());
        incident.setCategory(request.getCategory());
        incident.setStatus(request.getStatus() != null ? request.getStatus() : incident.getStatus());
        incident.setRootCause(request.getRootCause());
        incident.setActionTaken(request.getActionTaken());

        if (request.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            incident.setDepartment(dept);
        }

        if (request.getProjectId() != null) {
            Project proj = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            incident.setProject(proj);
        }

        Incident updated = incidentRepository.save(incident);
        return incidentMapper.toResponse(updated);
    }

    public void deleteIncident(Long id) {
        if (!incidentRepository.existsById(id)) {
            throw new RuntimeException("Incident not found");
        }
        incidentRepository.deleteById(id);
    }

//    Incidents specific to a single project
    public List<IncidentResponse> getProjectIncidents(Long departmentId, Long projectId) {
        List<Incident> incidents = incidentRepository.findByProjectIdAndDepartmentId(projectId, departmentId);

        return incidents.stream().map(this::mapToDto).collect(Collectors.toList());
    }


    public IncidentResponse updateIncidentByDepartment(Long incidentId, IncidentRequest request) {
        Incident incident = incidentRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident not found"));

        // Only update editable fields
        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setStartDate(request.getStartDate());
        incident.setEndDate(request.getEndDate());
        incident.setSeverity(request.getSeverity());
        incident.setCategory(request.getCategory());
        incident.setStatus(request.getStatus());
        incident.setRootCause(request.getRootCause());
        incident.setActionTaken(request.getActionTaken());

        // Do NOT update departmentId or projectId
        // incident.setDepartment(...)   // skip
        // incident.setProject(...)      // skip

        Incident updated = incidentRepository.save(incident);
        return mapToDto(updated);
    }



    public IncidentResponse getIncident(Long departmentId, Long projectId, Long incidentId) {
        // Optional: validate department and project exist
        Incident incident = incidentRepository.findByIdAndProjectIdAndDepartmentId(
                        incidentId, projectId, departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found"));

        return mapToDto(incident);
    }

    public Incident findIncident(Long departmentId, Long projectId, Long incidentId) {
        return incidentRepository
                .findByIdAndProjectIdAndDepartmentId(incidentId, projectId, departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found"));
    }

    private IncidentResponse mapToDto(Incident incident) {
        return IncidentResponse.builder()
                .id(incident.getId())
                .title(incident.getTitle())
                .description(incident.getDescription())
                .dateRegistered(incident.getDateRegistered())
                .startDate(incident.getStartDate())
                .endDate(incident.getEndDate())
                .severity(incident.getSeverity())
                .category(incident.getCategory())
                .status(incident.getStatus())
                .rootCause(incident.getRootCause())
                .actionTaken(incident.getActionTaken())
                .department(incident.getDepartment() != null ? incident.getDepartment().getName() : null)
                .project(incident.getProject() != null ? incident.getProject().getName() : null)
                .build();
    }

}
