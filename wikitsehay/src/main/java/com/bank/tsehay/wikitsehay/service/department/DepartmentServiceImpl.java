package com.bank.tsehay.wikitsehay.service.department;

import com.bank.tsehay.wikitsehay.dto.department.CreateDepartmentRequest;
import com.bank.tsehay.wikitsehay.dto.department.CreateDepartmentResponse;
import com.bank.tsehay.wikitsehay.dto.department.DepartmentResponse;
import com.bank.tsehay.wikitsehay.mapper.DepartmentMapper;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public CreateDepartmentResponse createDepartment(CreateDepartmentRequest request) {
        Department department = DepartmentMapper.toEntity(request);
        Department saved = departmentRepository.save(department);
        return DepartmentMapper.toCreateResponse(saved);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, CreateDepartmentRequest request) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        Department updated = departmentRepository.save(existing);
        return DepartmentMapper.toResponse(updated);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(DepartmentMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toResponse)
                .collect(Collectors.toList());
    }
}

