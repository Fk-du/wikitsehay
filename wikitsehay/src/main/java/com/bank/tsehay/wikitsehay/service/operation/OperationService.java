package com.bank.tsehay.wikitsehay.service.operation;


import com.bank.tsehay.wikitsehay.dto.operation.OperationRequest;
import com.bank.tsehay.wikitsehay.dto.operation.OperationResponse;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.operation.Operation;
import com.bank.tsehay.wikitsehay.repository.DepartmentRepository;
import com.bank.tsehay.wikitsehay.repository.operation.OperationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;
    private final DepartmentRepository departmentRepository;

    public OperationResponse createOperation(OperationRequest request) {
        Department dept = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Operation operation = Operation.builder()
                .name(request.getName())
                .description(request.getDescription())
                .vendor(request.getVendor())
                .status(request.getStatus())
                .sla(request.getSla())
                .criticality(request.getCriticality())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .ownerDepartment(dept)
                .build();

        return toResponse(operationRepository.save(operation));
    }

    public List<OperationResponse> getAllOperations() {
        return operationRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public long countByDepartment(Long departmentId) {
        return operationRepository.countByOwnerDepartmentId(departmentId);
    }


    public List<OperationResponse> getByDepartment(Long departmentId) {
        return operationRepository.findByOwnerDepartmentId(departmentId)
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
    }

    public OperationResponse getOperationById(Long id) {
        return operationRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Operation not found"));
    }

    public long countByDepartmentAndStatus(Long departmentId, String status) {
        return operationRepository.countByOwnerDepartmentIdAndStatus(departmentId, status);
    }

    public OperationResponse updateOperation(Long id, OperationRequest request) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operation not found"));

        Department dept = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        operation.setName(request.getName());
        operation.setDescription(request.getDescription());
        operation.setVendor(request.getVendor());
        operation.setStatus(request.getStatus());
        operation.setSla(request.getSla());
        operation.setCriticality(request.getCriticality());
        operation.setStartDate(request.getStartDate());
        operation.setEndDate(request.getEndDate());
        operation.setOwnerDepartment(dept);

        return toResponse(operationRepository.save(operation));
    }

    public void deleteOperation(Long id) {
        operationRepository.deleteById(id);
    }

    public OperationResponse getOperation(Long departmentId, Long operationId) {
        Operation operation = operationRepository.findByIdAndOwnerDepartment_Id(operationId, departmentId)
                .orElseThrow(() -> new RuntimeException("Operation not found"));

        return OperationResponse.builder()
                .id(operation.getId())
                .name(operation.getName())
                .description(operation.getDescription())
                .vendor(operation.getVendor())
                .status(operation.getStatus())
                .sla(operation.getSla())
                .criticality(operation.getCriticality())
                .startDate(operation.getStartDate())
                .endDate(operation.getEndDate())
                .departmentName(operation.getOwnerDepartment().getName())
                .build();
    }

    public OperationResponse updateOperationByOperationId(Long departmentId, Long operationId, OperationRequest request) {
        Operation operation = operationRepository.findById(operationId)
                .orElseThrow(() -> new EntityNotFoundException("Operation not found"));

        if (!operation.getOwnerDepartment().getId().equals(departmentId)) {
            throw new IllegalArgumentException("Operation does not belong to this department");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        // 🔹 Update fields
        operation.setName(request.getName());
        operation.setDescription(request.getDescription());
        operation.setVendor(request.getVendor());
        operation.setStatus(request.getStatus());
        operation.setSla(request.getSla());
        operation.setCriticality(request.getCriticality());
        operation.setStartDate(request.getStartDate());
        operation.setEndDate(request.getEndDate());
        operation.setOwnerDepartment(department);

        Operation updated = operationRepository.save(operation);

        return new OperationResponse(
                updated.getId(),
                updated.getName(),
                updated.getDescription(),
                updated.getVendor(),
                updated.getStatus(),
                updated.getSla(),
                updated.getCriticality(),
                updated.getStartDate(),
                updated.getEndDate(),
                updated.getOwnerDepartment().getName()
        );
    }

    private OperationResponse toResponse(Operation operation) {
        OperationResponse response = new OperationResponse();
        response.setId(operation.getId());
        response.setName(operation.getName());
        response.setDescription(operation.getDescription());
        response.setVendor(operation.getVendor());
        response.setStatus(operation.getStatus());
        response.setSla(operation.getSla());
        response.setCriticality(operation.getCriticality());
        response.setStartDate(operation.getStartDate());
        response.setEndDate(operation.getEndDate());
        response.setDepartmentName(operation.getOwnerDepartment().getName());
        return response;
    }
}

