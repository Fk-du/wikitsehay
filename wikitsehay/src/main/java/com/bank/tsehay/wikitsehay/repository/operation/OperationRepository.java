package com.bank.tsehay.wikitsehay.repository.operation;



import com.bank.tsehay.wikitsehay.model.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}

