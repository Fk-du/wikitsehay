package com.bank.tsehay.wikitsehay.controller.project;

import com.bank.tsehay.wikitsehay.dto.project.IncidentRequest;
import com.bank.tsehay.wikitsehay.dto.project.IncidentResponse;
import com.bank.tsehay.wikitsehay.model.project.Incident;
import com.bank.tsehay.wikitsehay.repository.project.IncidentRepository;
import com.bank.tsehay.wikitsehay.service.project.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;
//    private final Incident incident;
    private final IncidentRepository incidentRepository;
    @PostMapping
    public ResponseEntity<IncidentResponse> createIncident(@RequestBody IncidentRequest request) {
        return ResponseEntity.ok(incidentService.createIncident(request));
    }

    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAllIncidents() {
        List<IncidentResponse> incidents = incidentService.getAllIncidents();
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponse> getIncident(@PathVariable Long id) {
        return ResponseEntity.ok(incidentService.getIncident(id));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<IncidentResponse>> getByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(incidentService.getByDepartment(departmentId));
    }


    //    Incidents specific to a single project
    @GetMapping("/department/{departmentId}/{projectId}")
    public ResponseEntity<List<IncidentResponse>> getProjectIncidents(
            @PathVariable Long departmentId,
            @PathVariable Long projectId) {

        List<IncidentResponse> incidents = incidentService.getProjectIncidents(departmentId, projectId);
        return ResponseEntity.ok(incidents);
    }



    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<IncidentResponse>> getByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(incidentService.getByProject(projectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidentResponse> updateIncident(@PathVariable Long id,
                                                           @RequestBody IncidentRequest request) {
        return ResponseEntity.ok(incidentService.updateIncident(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/department/{departmentId}/{projectId}/{incidentId}")
    public ResponseEntity<IncidentResponse> updateIncident(
            @PathVariable Long departmentId,
            @PathVariable Long projectId,
            @PathVariable Long incidentId,
            @RequestBody IncidentRequest request) {

        // Optional: validate that incident belongs to this project & department
        Incident incident = incidentRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident not found"));

        if (!incident.getProject().getId().equals(projectId) ||
                !incident.getDepartment().getId().equals(departmentId)) {
            throw new RuntimeException("Incident does not belong to specified project/department");
        }

        IncidentResponse response = incidentService.updateIncident(incidentId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/department/{departmentId}/{projectId}/{incidentId}")
    public ResponseEntity<Incident> getIncident(
            @PathVariable Long departmentId,
            @PathVariable Long projectId,
            @PathVariable Long incidentId) {

        Incident incident = incidentService.findIncident(departmentId, projectId, incidentId);
        return ResponseEntity.ok(incident);
    }


}

