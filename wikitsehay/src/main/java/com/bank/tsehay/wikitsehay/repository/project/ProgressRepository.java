package com.bank.tsehay.wikitsehay.repository.project;

import com.bank.tsehay.wikitsehay.model.project.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    // Get all progress reports for a project
    List<Progress> findByProjectId(Long projectId);
}

