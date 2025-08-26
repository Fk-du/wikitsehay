package com.bank.tsehay.wikitsehay.controller.operation;

import com.bank.tsehay.wikitsehay.dto.operation.UptimeLogRequest;
import com.bank.tsehay.wikitsehay.dto.operation.UptimeLogResponse;
import com.bank.tsehay.wikitsehay.service.operation.UptimeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/uptime-logs")
@RequiredArgsConstructor
public class UptimeLogController {

    private final UptimeLogService uptimeLogService;

    @PostMapping
    public ResponseEntity<UptimeLogResponse> createLog(@RequestBody UptimeLogRequest request) {
        return ResponseEntity.ok(uptimeLogService.createUptimeLog(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UptimeLogResponse> getLog(@PathVariable Long id) {
        return ResponseEntity.ok(uptimeLogService.getUptimeLog(id));
    }

    @GetMapping
    public ResponseEntity<List<UptimeLogResponse>> getAllLogs() {
        return ResponseEntity.ok(uptimeLogService.getAllUptimeLogs());
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<UptimeLogResponse>> getLogsByService(@PathVariable Long serviceId) {
        return ResponseEntity.ok(uptimeLogService.getLogsByService(serviceId));
    }

    @GetMapping("/period/{periodType}")
    public ResponseEntity<List<UptimeLogResponse>> getLogsByPeriod(@PathVariable String periodType) {
        return ResponseEntity.ok(uptimeLogService.getLogsByPeriod(periodType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UptimeLogResponse> updateLog(
            @PathVariable Long id,
            @RequestBody UptimeLogRequest request) {
        return ResponseEntity.ok(uptimeLogService.updateUptimeLog(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        uptimeLogService.deleteUptimeLog(id);
        return ResponseEntity.noContent().build();
    }
}

