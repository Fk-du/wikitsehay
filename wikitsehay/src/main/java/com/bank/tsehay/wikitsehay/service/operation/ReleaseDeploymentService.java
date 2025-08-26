package com.bank.tsehay.wikitsehay.service.operation;

import com.bank.tsehay.wikitsehay.dto.operation.ReleaseDeploymentRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ReleaseDeploymentResponse;

import java.util.List;

public interface ReleaseDeploymentService {
    ReleaseDeploymentResponse createDeployment(ReleaseDeploymentRequest request);
    ReleaseDeploymentResponse getDeployment(Long id);
    List<ReleaseDeploymentResponse> getAllDeployments();
    List<ReleaseDeploymentResponse> getDeploymentsByService(Long serviceId);
    ReleaseDeploymentResponse updateDeployment(Long id, ReleaseDeploymentRequest request);
    void deleteDeployment(Long id);
}

