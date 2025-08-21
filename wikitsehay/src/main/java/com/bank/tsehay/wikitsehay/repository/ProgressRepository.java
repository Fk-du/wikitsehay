package com.bank.tsehay.wikitsehay.repository;

import com.bank.tsehay.wikitsehay.model.project.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
}

