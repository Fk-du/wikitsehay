package com.bank.tsehay.wikitsehay.service.project;

import com.bank.tsehay.wikitsehay.dto.project.ProjectRequest;
import com.bank.tsehay.wikitsehay.dto.project.ProjectResponse;
import com.bank.tsehay.wikitsehay.mapper.ProjectMapper;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.project.Project;
import com.bank.tsehay.wikitsehay.repository.DepartmentRepository;
import com.bank.tsehay.wikitsehay.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectMapper projectMapper;

    public ProjectResponse createProject(ProjectRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Project project = Project.builder()
                .name(request.getName())
                .charter(request.getCharter())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(request.getStatus())
                .department(department)
                .build();

        return projectMapper.toResponse(projectRepository.save(project));
    }

    public ProjectResponse getProject(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll()
                .stream().map(projectMapper::toResponse).toList();
    }

    public List<ProjectResponse> getByDepartment(Long departmentId) {
        return projectRepository.findByDepartmentId(departmentId)
                .stream().map(projectMapper::toResponse).toList();
    }

    public long countByDepartment(Long departmentId) {
        return projectRepository.countByDepartmentId(departmentId);
    }

    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(request.getName());
        project.setCharter(request.getCharter());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setStatus(request.getStatus());

        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            project.setDepartment(department);
        }

        return projectMapper.toResponse(projectRepository.save(project));
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);
    }
}
