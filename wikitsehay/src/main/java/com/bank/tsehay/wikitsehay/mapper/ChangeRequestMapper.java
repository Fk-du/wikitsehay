package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.operation.ChangeRequestRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ChangeRequestResponse;
import com.bank.tsehay.wikitsehay.model.operation.ChangeRequest;
import com.bank.tsehay.wikitsehay.model.operation.Operation;
import com.bank.tsehay.wikitsehay.model.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChangeRequestMapper {

    public ChangeRequest toEntity(
            ChangeRequestRequest request,
            Operation service,
            User requester,
            User approvedBy,
            User implementedBy
    ) {
        ChangeRequest changeRequest = new ChangeRequest();
        changeRequest.setRequestDate(LocalDateTime.now()); // auto set
        changeRequest.setType(request.getType());
        changeRequest.setApprovalStatus(request.getApprovalStatus());
        changeRequest.setImplementationDate(request.getImplementationDate());
        changeRequest.setService(service);
        changeRequest.setRequester(requester);
        changeRequest.setApprovedBy(approvedBy);
        changeRequest.setImplementedBy(implementedBy);
        return changeRequest;
    }

    public ChangeRequestResponse toResponse(ChangeRequest changeRequest) {
        ChangeRequestResponse response = new ChangeRequestResponse();
        response.setId(changeRequest.getId());
        response.setRequestDate(changeRequest.getRequestDate());
        response.setType(changeRequest.getType());
        response.setApprovalStatus(changeRequest.getApprovalStatus());
        response.setImplementationDate(changeRequest.getImplementationDate());
        response.setServiceId(changeRequest.getService().getId());
        response.setRequesterId(changeRequest.getRequester() != null ? changeRequest.getRequester().getId() : null);
        response.setApprovedById(changeRequest.getApprovedBy() != null ? changeRequest.getApprovedBy().getId() : null);
        response.setImplementedById(changeRequest.getImplementedBy() != null ? changeRequest.getImplementedBy().getId() : null);
        return response;
    }
}
