package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.operation.ReleaseDeploymentRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ReleaseDeploymentResponse;
import com.bank.tsehay.wikitsehay.model.operation.Operation;
import com.bank.tsehay.wikitsehay.model.operation.ReleaseDeployment;
import com.bank.tsehay.wikitsehay.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class ReleaseDeploymentMapper {

    public ReleaseDeployment toEntity(
            ReleaseDeploymentRequest request,
            Operation service,
            User deployedBy,
            User approvedBy
    ) {
        ReleaseDeployment deployment = new ReleaseDeployment();
        deployment.setVersion(request.getVersion());
        deployment.setReleaseNotes(request.getReleaseNotes());
        deployment.setDeployedDate(request.getDeployedDate());
        deployment.setRollbackPlan(request.getRollbackPlan());
        deployment.setService(service);
        deployment.setDeployedBy(deployedBy);
        deployment.setApprovedBy(approvedBy);
        return deployment;
    }

    public ReleaseDeploymentResponse toResponse(ReleaseDeployment deployment) {
        ReleaseDeploymentResponse response = new ReleaseDeploymentResponse();
        response.setId(deployment.getId());
        response.setVersion(deployment.getVersion());
        response.setReleaseNotes(deployment.getReleaseNotes());
        response.setDeployedDate(deployment.getDeployedDate());
        response.setRollbackPlan(deployment.getRollbackPlan());
        response.setServiceId(deployment.getService().getId());
        response.setDeployedById(deployment.getDeployedBy() != null ? deployment.getDeployedBy().getId() : null);
        response.setApprovedById(deployment.getApprovedBy() != null ? deployment.getApprovedBy().getId() : null);
        return response;
    }
}

