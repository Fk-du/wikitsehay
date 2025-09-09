package com.bank.tsehay.wikitsehay.repository.operation;



import com.bank.tsehay.wikitsehay.model.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByOwnerDepartmentId(Long departmentId);
    Long countByOwnerDepartmentId(Long departmentId);
    long countByOwnerDepartmentIdAndStatus(Long departmentId, String status);
}

