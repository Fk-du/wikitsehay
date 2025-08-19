package com.bank.tsehay.wikitsehay.repository;

import com.bank.tsehay.wikitsehay.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
