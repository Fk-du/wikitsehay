package com.bank.tsehay.wikitsehay.repository.operation;

import com.bank.tsehay.wikitsehay.model.operation.ReleaseDeployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseDeploymentRepository extends JpaRepository<ReleaseDeployment, Long> {
    List<ReleaseDeployment> findByServiceId(Long serviceId);
}

