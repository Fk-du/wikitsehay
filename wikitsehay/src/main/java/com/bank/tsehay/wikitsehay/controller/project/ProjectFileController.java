package com.bank.tsehay.wikitsehay.controller.project;

import com.bank.tsehay.wikitsehay.dto.project.ProjectFileResponse;
import com.bank.tsehay.wikitsehay.service.project.ProjectFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/project-files")
@RequiredArgsConstructor
public class ProjectFileController {

    private final ProjectFileService projectFileService;

    @PostMapping("/upload")
    public ResponseEntity<ProjectFileResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("projectId") Long projectId) throws IOException {

        return ResponseEntity.ok(projectFileService.uploadFile(file, projectId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectFileResponse>> getFilesByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectFileService.getFilesByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectFileResponse> getFile(@PathVariable Long id) {
        return ResponseEntity.ok(projectFileService.getFile(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) throws IOException {
        projectFileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}

