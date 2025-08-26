package com.bank.tsehay.wikitsehay.controller.operation;

import com.bank.tsehay.wikitsehay.dto.operation.ChangeRequestRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ChangeRequestResponse;
import com.bank.tsehay.wikitsehay.service.operation.ChangeRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/change-requests")
@RequiredArgsConstructor
public class ChangeRequestController {

    private final ChangeRequestService changeRequestService;

    @PostMapping
    public ResponseEntity<ChangeRequestResponse> createChangeRequest(@RequestBody ChangeRequestRequest request) {
        return ResponseEntity.ok(changeRequestService.createChangeRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChangeRequestResponse> getChangeRequest(@PathVariable Long id) {
        return ResponseEntity.ok(changeRequestService.getChangeRequest(id));
    }

    @GetMapping
    public ResponseEntity<List<ChangeRequestResponse>> getAllChangeRequests() {
        return ResponseEntity.ok(changeRequestService.getAllChangeRequests());
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ChangeRequestResponse>> getByService(@PathVariable Long serviceId) {
        return ResponseEntity.ok(changeRequestService.getChangeRequestsByService(serviceId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ChangeRequestResponse>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(changeRequestService.getChangeRequestsByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChangeRequestResponse> updateChangeRequest(
            @PathVariable Long id,
            @RequestBody ChangeRequestRequest request) {
        return ResponseEntity.ok(changeRequestService.updateChangeRequest(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChangeRequest(@PathVariable Long id) {
        changeRequestService.deleteChangeRequest(id);
        return ResponseEntity.noContent().build();
    }
}
