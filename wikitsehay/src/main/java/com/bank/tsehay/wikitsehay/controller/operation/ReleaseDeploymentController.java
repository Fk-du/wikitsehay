package com.bank.tsehay.wikitsehay.controller.operation;

import com.bank.tsehay.wikitsehay.dto.operation.ReleaseDeploymentRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ReleaseDeploymentResponse;
import com.bank.tsehay.wikitsehay.service.operation.ReleaseDeploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/release-deployments")
@RequiredArgsConstructor
public class ReleaseDeploymentController {

    private final ReleaseDeploymentService deploymentService;

    @PostMapping
    public ResponseEntity<ReleaseDeploymentResponse> createDeployment(@RequestBody ReleaseDeploymentRequest request) {
        return ResponseEntity.ok(deploymentService.createDeployment(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReleaseDeploymentResponse> getDeployment(@PathVariable Long id) {
        return ResponseEntity.ok(deploymentService.getDeployment(id));
    }

    @GetMapping
    public ResponseEntity<List<ReleaseDeploymentResponse>> getAllDeployments() {
        return ResponseEntity.ok(deploymentService.getAllDeployments());
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ReleaseDeploymentResponse>> getDeploymentsByService(@PathVariable Long serviceId) {
        return ResponseEntity.ok(deploymentService.getDeploymentsByService(serviceId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReleaseDeploymentResponse> updateDeployment(
            @PathVariable Long id,
            @RequestBody ReleaseDeploymentRequest request) {
        return ResponseEntity.ok(deploymentService.updateDeployment(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeployment(@PathVariable Long id) {
        deploymentService.deleteDeployment(id);
        return ResponseEntity.noContent().build();
    }
}

