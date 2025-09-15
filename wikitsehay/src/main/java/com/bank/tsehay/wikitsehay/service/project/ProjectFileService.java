package com.bank.tsehay.wikitsehay.service.project;

import com.bank.tsehay.wikitsehay.dto.project.ProjectFileResponse;
import com.bank.tsehay.wikitsehay.model.project.Project;
import com.bank.tsehay.wikitsehay.model.project.ProjectFile;
import com.bank.tsehay.wikitsehay.repository.project.ProjectFileRepository;
import com.bank.tsehay.wikitsehay.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectFileService {

    private final ProjectFileRepository projectFileRepository;
    private final ProjectRepository projectRepository;

    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    public List<ProjectFileResponse> getFilesByProject(Long projectId) {
        return projectFileRepository.findByProjectId(projectId)
                .stream().map(ProjectFileResponse::new)
                .toList();
    }

    public ProjectFileResponse getFile(Long id) {
        return projectFileRepository.findById(id)
                .map(ProjectFileResponse::new)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    public void deleteFile(Long id) throws IOException {
        ProjectFile file = projectFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        // Delete from filesystem
        Files.deleteIfExists(Paths.get(file.getFilePath()));

        projectFileRepository.delete(file);
    }

    public ProjectFile storeFile(Long projectId, MultipartFile file) throws IOException {
        Files.createDirectories(fileStorageLocation);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        ProjectFile projectFile = ProjectFile.builder()
                .fileName(fileName)
                .fileType(file.getContentType())
                .uploadDate(LocalDate.now())
                .filePath(targetLocation.toString())
                .project(Project.builder().id(projectId).build())
                .build();

        return projectFileRepository.save(projectFile);
    }

    public ResponseEntity<Resource> loadFileAsResource(Long fileId) {
        ProjectFile projectFile = projectFileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Path filePath = Paths.get(projectFile.getFilePath());
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
            if(!resource.exists()) throw new RuntimeException("File not found");
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found", e);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(projectFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + projectFile.getFileName() + "\"")
                .body(resource);
    }
}

