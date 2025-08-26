package com.bank.tsehay.wikitsehay.repository.project;

import com.bank.tsehay.wikitsehay.model.project.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findByProjectId(Long projectId);
}

