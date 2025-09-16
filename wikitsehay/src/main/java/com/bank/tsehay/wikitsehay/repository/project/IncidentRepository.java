package com.bank.tsehay.wikitsehay.repository.project;

import com.bank.tsehay.wikitsehay.model.project.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByDepartmentId(Long departmentId);
    List<Incident> findByProjectId(Long projectId);
    List<Incident> findByTitleContainingIgnoreCase(String title);
    List<Incident> findByProjectIdAndDepartmentId(Long projectId, Long departmentId);
    Optional<Incident> findByIdAndProjectIdAndDepartmentId(Long id, Long projectId, Long departmentId);
}
