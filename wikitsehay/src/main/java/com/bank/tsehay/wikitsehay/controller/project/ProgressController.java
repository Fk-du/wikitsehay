package com.bank.tsehay.wikitsehay.controller.project;

import com.bank.tsehay.wikitsehay.dto.project.ProgressRequest;
import com.bank.tsehay.wikitsehay.dto.project.ProgressResponse;
import com.bank.tsehay.wikitsehay.service.project.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping
    public ResponseEntity<ProgressResponse> createProgress(@RequestBody ProgressRequest request) {
        return ResponseEntity.ok(progressService.createProgress(request));
    }

    @GetMapping
    public ResponseEntity<List<ProgressResponse>> getAllProgress() {
        return ResponseEntity.ok(progressService.getAllProgress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressResponse> getProgressById(@PathVariable Long id) {
        return ResponseEntity.ok(progressService.getProgressById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgressResponse> updateProgress(@PathVariable Long id,
                                                           @RequestBody ProgressRequest request) {
        return ResponseEntity.ok(progressService.updateProgress(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        progressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProgressResponse>> getProjectProgress(@PathVariable Long projectId) {
        List<ProgressResponse> progressList = progressService.getProjectProgress(projectId);
        return ResponseEntity.ok(progressList);
    }
}

