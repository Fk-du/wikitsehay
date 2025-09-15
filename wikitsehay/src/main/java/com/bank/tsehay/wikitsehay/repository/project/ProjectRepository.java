package com.bank.tsehay.wikitsehay.repository.project;

import com.bank.tsehay.wikitsehay.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByDepartmentId(Long departmentId);
    Long countByDepartmentId(Long departmentId);
    List<Project> findByNameContainingIgnoreCase(String name);

    Optional<Project> findByIdAndDepartmentId(Long projectId, Long departmentId);

}
