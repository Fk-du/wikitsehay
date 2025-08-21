package com.bank.tsehay.wikitsehay.service.department;

import com.bank.tsehay.wikitsehay.dto.department.CreateDepartmentRequest;
import com.bank.tsehay.wikitsehay.dto.department.CreateDepartmentResponse;
import com.bank.tsehay.wikitsehay.dto.department.DepartmentResponse;


import java.util.List;

public interface DepartmentService {
    CreateDepartmentResponse createDepartment(CreateDepartmentRequest request);
    DepartmentResponse updateDepartment(Long id, CreateDepartmentRequest request);
    void deleteDepartment(Long id);
    DepartmentResponse getDepartmentById(Long id);
    List<DepartmentResponse> getAllDepartments();
}