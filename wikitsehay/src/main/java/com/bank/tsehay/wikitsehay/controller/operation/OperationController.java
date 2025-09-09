package com.bank.tsehay.wikitsehay.controller.operation;


import com.bank.tsehay.wikitsehay.dto.operation.OperationRequest;
import com.bank.tsehay.wikitsehay.dto.operation.OperationResponse;
import com.bank.tsehay.wikitsehay.dto.project.ProjectResponse;
import com.bank.tsehay.wikitsehay.service.operation.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;

    @PostMapping
    public ResponseEntity<OperationResponse> createOperation(@RequestBody OperationRequest request) {
        return ResponseEntity.ok(operationService.createOperation(request));
    }

    @GetMapping
    public ResponseEntity<List<OperationResponse>> getAllOperations() {
        return ResponseEntity.ok(operationService.getAllOperations());
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<OperationResponse>> getByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(operationService.getByDepartment(departmentId));
    }

    @GetMapping("/department/{departmentId}/count")
    public ResponseEntity<Long> countByDepartment(@PathVariable Long departmentId) {
        long count = operationService.countByDepartment(departmentId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/department/{departmentId}/running/count")
    public ResponseEntity<Long> countRunningServicesByDepartment(@PathVariable Long departmentId) {
        long count = operationService.countByDepartmentAndStatus(departmentId, "RUNNING");
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResponse> getOperationById(@PathVariable Long id) {
        return ResponseEntity.ok(operationService.getOperationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationResponse> updateOperation(
            @PathVariable Long id, @RequestBody OperationRequest request) {
        return ResponseEntity.ok(operationService.updateOperation(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
        return ResponseEntity.noContent().build();
    }
}

