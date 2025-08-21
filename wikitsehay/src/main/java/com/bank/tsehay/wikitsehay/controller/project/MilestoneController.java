package com.bank.tsehay.wikitsehay.controller.project;

import com.bank.tsehay.wikitsehay.dto.project.MilestoneRequest;
import com.bank.tsehay.wikitsehay.dto.project.MilestoneResponse;
import com.bank.tsehay.wikitsehay.service.project.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milestones")
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @PostMapping
    public ResponseEntity<MilestoneResponse> createMilestone(@RequestBody MilestoneRequest request) {
        return ResponseEntity.ok(milestoneService.createMilestone(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilestoneResponse> getMilestone(@PathVariable Long id) {
        return ResponseEntity.ok(milestoneService.getMilestone(id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<MilestoneResponse>> getAllMilestones(@PathVariable Long projectId) {
        return ResponseEntity.ok(milestoneService.getAllMilestones(projectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MilestoneResponse> updateMilestone(
            @PathVariable Long id,
            @RequestBody MilestoneRequest request
    ) {
        return ResponseEntity.ok(milestoneService.updateMilestone(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Long id) {
        milestoneService.deleteMilestone(id);
        return ResponseEntity.noContent().build();
    }
}

