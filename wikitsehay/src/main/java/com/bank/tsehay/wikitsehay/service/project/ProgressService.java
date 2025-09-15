package com.bank.tsehay.wikitsehay.service.project;

import com.bank.tsehay.wikitsehay.dto.project.ProgressRequest;
import com.bank.tsehay.wikitsehay.dto.project.ProgressResponse;
import com.bank.tsehay.wikitsehay.model.project.Progress;
import com.bank.tsehay.wikitsehay.model.project.Project;
import com.bank.tsehay.wikitsehay.repository.project.ProgressRepository;
import com.bank.tsehay.wikitsehay.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public ProgressResponse createProgress(ProgressRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Progress progress = new Progress();
        progress.setReportDate(request.getReportDate());
        progress.setSummary(request.getSummary());
        progress.setProject(project);

        Progress saved = progressRepository.save(progress);
        return mapToResponse(saved);
    }

    public List<ProgressResponse> getAllProgress() {
        return progressRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProgressResponse getProgressById(Long id) {
        return progressRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Progress not found"));
    }

    @Transactional
    public ProgressResponse updateProgress(Long id, ProgressRequest request) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        progress.setReportDate(request.getReportDate());
        progress.setSummary(request.getSummary());
        progress.setProject(project);

        return mapToResponse(progressRepository.save(progress));
    }

    @Transactional
    public void deleteProgress(Long id) {
        progressRepository.deleteById(id);
    }

    private ProgressResponse mapToResponse(Progress progress) {
        ProgressResponse response = new ProgressResponse();
        response.setId(progress.getId());
        response.setReportDate(progress.getReportDate());
        response.setSummary(progress.getSummary());
        response.setProjectName(progress.getProject().getName());
        return response;
    }

    public List<ProgressResponse> getProjectProgress(Long projectId) {
        List<Progress> progressList = progressRepository.findByProjectId(projectId);

        return progressList.stream().map(p -> ProgressResponse.builder()
                .id(p.getId())
                .reportDate(p.getReportDate())
                .summary(p.getSummary())
                .projectName(p.getProject().getName())
                .build()
        ).collect(Collectors.toList());
    }
}
