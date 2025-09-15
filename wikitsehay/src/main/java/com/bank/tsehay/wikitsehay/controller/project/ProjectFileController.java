package com.bank.tsehay.wikitsehay.controller.project;

import com.bank.tsehay.wikitsehay.dto.project.ProjectFileResponse;
import com.bank.tsehay.wikitsehay.model.project.ProjectFile;
import com.bank.tsehay.wikitsehay.service.project.ProjectFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class ProjectFileController {

    private final ProjectFileService fileService;

    @PostMapping("/upload/{projectId}")
    public ResponseEntity<?> uploadFile(
            @PathVariable Long projectId,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            ProjectFile savedFile = fileService.storeFile(projectId, file);
            return ResponseEntity.ok(savedFile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        return fileService.loadFileAsResource(fileId);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectFileResponse>> getFilesByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(fileService.getFilesByProject(projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectFileResponse> getFile(@PathVariable Long id) {
        return ResponseEntity.ok(fileService.getFile(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) throws IOException {
        fileService.deleteFile(id);
        return ResponseEntity.ok("File deleted successfully");
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewFile(@PathVariable Long id) throws MalformedURLException {
        ProjectFileResponse file = fileService.getFile(id);
        Path filePath = Paths.get(file.getFilePath());
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // adjust dynamically if needed
                .body(resource);
    }


}

