package com.bank.tsehay.wikitsehay.repository.operation;



import com.bank.tsehay.wikitsehay.model.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByOwnerDepartmentId(Long departmentId);
    Long countByOwnerDepartmentId(Long departmentId);
    long countByOwnerDepartmentIdAndStatus(Long departmentId, String status);
    List<Operation> findByNameContainingIgnoreCase(String name);

    Optional<Operation> findByIdAndOwnerDepartment_Id(Long operationId, Long departmentId);

}

