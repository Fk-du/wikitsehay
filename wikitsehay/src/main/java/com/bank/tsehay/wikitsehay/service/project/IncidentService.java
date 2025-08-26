package com.bank.tsehay.wikitsehay.service.project;

import com.bank.tsehay.wikitsehay.dto.project.IncidentRequest;
import com.bank.tsehay.wikitsehay.dto.project.IncidentResponse;
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

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final IncidentMapper incidentMapper;
    private final AuthService authService;

    private final UserService userService; // to fetch current user

    private User getCurrentUser() {
        return authService.getCurrentUser(); // from JWT/session
    }

    private void checkDepartmentAccess(Department department) {
        User user = getCurrentUser();

        // SUPER_USER bypass (later)
        if (user.getRole().getName().equals("SUPER_USER")) {
            return;
        }

        // must belong to same department
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
                .department(dept)
                .project(proj)
                .build();

        return incidentMapper.toResponse(incidentRepository.save(incident));
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
}

