package com.bank.tsehay.wikitsehay.mapper;


import com.bank.tsehay.wikitsehay.dto.department.CreateDepartmentRequest;
import com.bank.tsehay.wikitsehay.dto.department.CreateDepartmentResponse;
import com.bank.tsehay.wikitsehay.dto.department.DepartmentResponse;
import com.bank.tsehay.wikitsehay.model.Department;

public class DepartmentMapper {

    public static Department toEntity(CreateDepartmentRequest request) {
        Department department = new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        return department;
    }

    public static CreateDepartmentResponse toCreateResponse(Department department) {
        return CreateDepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }

    public static DepartmentResponse toResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }
}

