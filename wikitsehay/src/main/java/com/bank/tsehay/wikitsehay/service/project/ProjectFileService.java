package com.bank.tsehay.wikitsehay.service.project;

import com.bank.tsehay.wikitsehay.dto.project.ProjectFileResponse;
import com.bank.tsehay.wikitsehay.model.project.Project;
import com.bank.tsehay.wikitsehay.model.project.ProjectFile;
import com.bank.tsehay.wikitsehay.repository.project.ProjectFileRepository;
import com.bank.tsehay.wikitsehay.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    private final String uploadDir = "uploads/"; // local folder

    public ProjectFileResponse uploadFile(MultipartFile file, Long projectId) throws IOException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(uploadDir + fileName);
        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        ProjectFile projectFile = ProjectFile.builder()
                .fileName(fileName)
                .fileType(file.getContentType())
                .uploadDate(LocalDate.now())
                .filePath(path.toString())
                .project(project)
                .build();

        projectFileRepository.save(projectFile);
        return new ProjectFileResponse(projectFile);
    }

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
}

