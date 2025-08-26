package com.bank.tsehay.wikitsehay.controller.operation;

import com.bank.tsehay.wikitsehay.dto.operation.ReleaseNoteRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ReleaseNoteResponse;
import com.bank.tsehay.wikitsehay.service.operation.ReleaseNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/release-notes")
@RequiredArgsConstructor
public class ReleaseNoteController {

    private final ReleaseNoteService releaseNoteService;

    @PostMapping
    public ResponseEntity<ReleaseNoteResponse> createReleaseNote(@RequestBody ReleaseNoteRequest request) {
        return ResponseEntity.ok(releaseNoteService.createReleaseNote(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReleaseNoteResponse> getReleaseNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(releaseNoteService.getReleaseNoteById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReleaseNoteResponse>> getAllReleaseNotes() {
        return ResponseEntity.ok(releaseNoteService.getAllReleaseNotes());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ReleaseNoteResponse>> getReleaseNotesByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(releaseNoteService.getReleaseNotesByProject(projectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReleaseNoteResponse> updateReleaseNote(
            @PathVariable Long id,
            @RequestBody ReleaseNoteRequest request) {
        return ResponseEntity.ok(releaseNoteService.updateReleaseNote(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReleaseNote(@PathVariable Long id) {
        releaseNoteService.deleteReleaseNote(id);
        return ResponseEntity.noContent().build();
    }
}
