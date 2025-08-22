package com.bank.tsehay.wikitsehay.controller.operation;

import com.bank.tsehay.wikitsehay.dto.operation.OperationalIncidentRequest;
import com.bank.tsehay.wikitsehay.dto.operation.OperationalIncidentResponse;
import com.bank.tsehay.wikitsehay.service.operation.OperationalIncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidents")
@RequiredArgsConstructor
public class OperationalIncidentController {
    private final OperationalIncidentService incidentService;

    @PostMapping
    public ResponseEntity<OperationalIncidentResponse> createIncident(@RequestBody OperationalIncidentRequest request) {
        return ResponseEntity.ok(incidentService.createIncident(request));
    }

    @GetMapping
    public ResponseEntity<List<OperationalIncidentResponse>> getAllIncidents() {
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationalIncidentResponse> getIncidentById(@PathVariable Long id) {
        return ResponseEntity.ok(incidentService.getIncidentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationalIncidentResponse> updateIncident(
            @PathVariable Long id, @RequestBody OperationalIncidentRequest request) {
        return ResponseEntity.ok(incidentService.updateIncident(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }
}
