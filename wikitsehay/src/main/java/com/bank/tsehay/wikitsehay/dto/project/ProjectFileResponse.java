package com.bank.tsehay.wikitsehay.dto.project;

import com.bank.tsehay.wikitsehay.model.project.ProjectFile;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectFileResponse {
    private Long id;
    private String fileName;
    private String fileType;
    private LocalDate uploadDate;
    private String filePath;
    private Long projectId;

    public ProjectFileResponse(ProjectFile file) {
        this.id = file.getId();
        this.fileName = file.getFileName();
        this.fileType = file.getFileType();
        this.uploadDate = file.getUploadDate();
        this.filePath = file.getFilePath();
        this.projectId = file.getProject().getId();
    }
}

