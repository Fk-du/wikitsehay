package com.bank.tsehay.wikitsehay.repository.operation;

import com.bank.tsehay.wikitsehay.model.operation.ChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeRequestRepository extends JpaRepository<ChangeRequest, Long> {
    List<ChangeRequest> findByServiceId(Long serviceId);
    List<ChangeRequest> findByApprovalStatus(String status);
}
