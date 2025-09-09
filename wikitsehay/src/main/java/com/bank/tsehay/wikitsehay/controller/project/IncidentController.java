package com.bank.tsehay.wikitsehay.controller.project;

import com.bank.tsehay.wikitsehay.dto.project.IncidentRequest;
import com.bank.tsehay.wikitsehay.dto.project.IncidentResponse;
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
}

