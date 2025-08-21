package com.bank.tsehay.wikitsehay.service.project;

import com.bank.tsehay.wikitsehay.dto.project.MilestoneRequest;
import com.bank.tsehay.wikitsehay.dto.project.MilestoneResponse;
import com.bank.tsehay.wikitsehay.model.project.Milestone;
import com.bank.tsehay.wikitsehay.model.project.Project;
import com.bank.tsehay.wikitsehay.repository.MilestoneRepository;
import com.bank.tsehay.wikitsehay.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    public MilestoneResponse createMilestone(MilestoneRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Milestone milestone = Milestone.builder()
                .name(request.getName())
                .dueDate(request.getDueDate())
                .status(request.getStatus())
                .project(project)
                .build();

        milestoneRepository.save(milestone);

        return mapToResponse(milestone);
    }

    public MilestoneResponse getMilestone(Long id) {
        return milestoneRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));
    }

    public List<MilestoneResponse> getAllMilestones(Long projectId) {
        return milestoneRepository.findByProjectId(projectId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public MilestoneResponse updateMilestone(Long id, MilestoneRequest request) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));

        milestone.setName(request.getName());
        milestone.setDueDate(request.getDueDate());
        milestone.setStatus(request.getStatus());

        milestoneRepository.save(milestone);
        return mapToResponse(milestone);
    }

    public void deleteMilestone(Long id) {
        milestoneRepository.deleteById(id);
    }

    private MilestoneResponse mapToResponse(Milestone milestone) {
        return MilestoneResponse.builder()
                .id(milestone.getId())
                .name(milestone.getName())
                .dueDate(milestone.getDueDate())
                .status(milestone.getStatus())
                .projectId(milestone.getProject().getId())
                .build();
    }
}
