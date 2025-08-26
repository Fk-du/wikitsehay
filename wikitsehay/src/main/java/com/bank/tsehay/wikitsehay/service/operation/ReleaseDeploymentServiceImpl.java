package com.bank.tsehay.wikitsehay.service.operation;

import com.bank.tsehay.wikitsehay.dto.operation.ReleaseDeploymentRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ReleaseDeploymentResponse;
import com.bank.tsehay.wikitsehay.mapper.ReleaseDeploymentMapper;
import com.bank.tsehay.wikitsehay.model.operation.Operation;
import com.bank.tsehay.wikitsehay.model.operation.ReleaseDeployment;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.repository.UserRepository;
import com.bank.tsehay.wikitsehay.repository.operation.OperationRepository;
import com.bank.tsehay.wikitsehay.repository.operation.ReleaseDeploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReleaseDeploymentServiceImpl implements ReleaseDeploymentService {

    private final ReleaseDeploymentRepository deploymentRepository;
    private final OperationRepository operationRepository;
    private final UserRepository userRepository;
    private final ReleaseDeploymentMapper mapper;

    @Override
    public ReleaseDeploymentResponse createDeployment(ReleaseDeploymentRequest request) {
        Operation service = operationRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        User deployedBy = request.getDeployedById() != null ?
                userRepository.findById(request.getDeployedById())
                        .orElseThrow(() -> new RuntimeException("DeployedBy user not found"))
                : null;

        User approvedBy = request.getApprovedById() != null ?
                userRepository.findById(request.getApprovedById())
                        .orElseThrow(() -> new RuntimeException("ApprovedBy user not found"))
                : null;

        ReleaseDeployment deployment = mapper.toEntity(request, service, deployedBy, approvedBy);
        return mapper.toResponse(deploymentRepository.save(deployment));
    }

    @Override
    public ReleaseDeploymentResponse getDeployment(Long id) {
        ReleaseDeployment deployment = deploymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deployment not found"));
        return mapper.toResponse(deployment);
    }

    @Override
    public List<ReleaseDeploymentResponse> getAllDeployments() {
        return deploymentRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ReleaseDeploymentResponse> getDeploymentsByService(Long serviceId) {
        return deploymentRepository.findByServiceId(serviceId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ReleaseDeploymentResponse updateDeployment(Long id, ReleaseDeploymentRequest request) {
        ReleaseDeployment deployment = deploymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deployment not found"));

        Operation service = operationRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        User deployedBy = request.getDeployedById() != null ?
                userRepository.findById(request.getDeployedById())
                        .orElseThrow(() -> new RuntimeException("DeployedBy user not found"))
                : null;

        User approvedBy = request.getApprovedById() != null ?
                userRepository.findById(request.getApprovedById())
                        .orElseThrow(() -> new RuntimeException("ApprovedBy user not found"))
                : null;

        deployment.setVersion(request.getVersion());
        deployment.setReleaseNotes(request.getReleaseNotes());
        deployment.setDeployedDate(request.getDeployedDate());
        deployment.setRollbackPlan(request.getRollbackPlan());
        deployment.setService(service);
        deployment.setDeployedBy(deployedBy);
        deployment.setApprovedBy(approvedBy);

        return mapper.toResponse(deploymentRepository.save(deployment));
    }

    @Override
    public void deleteDeployment(Long id) {
        deploymentRepository.deleteById(id);
    }
}

