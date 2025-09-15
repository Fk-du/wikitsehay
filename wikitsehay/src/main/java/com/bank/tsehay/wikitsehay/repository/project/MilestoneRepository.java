package com.bank.tsehay.wikitsehay.repository.project;

import com.bank.tsehay.wikitsehay.model.project.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findByProjectId(Long projectId);
    // Find milestones by project and department
//    List<Milestone> findByProjectIdAndProjectOwnerDepartmentId(Long projectId, Long departmentId);
    // Find milestones by projectId and departmentId via project relation
    List<Milestone> findByProjectIdAndProjectDepartmentId(Long projectId, Long departmentId);
}

