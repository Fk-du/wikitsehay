package com.bank.tsehay.wikitsehay.service.operation;

import com.bank.tsehay.wikitsehay.dto.operation.ChangeRequestRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ChangeRequestResponse;

import java.util.List;

public interface ChangeRequestService {
    ChangeRequestResponse createChangeRequest(ChangeRequestRequest request);
    ChangeRequestResponse getChangeRequest(Long id);
    List<ChangeRequestResponse> getAllChangeRequests();
    List<ChangeRequestResponse> getChangeRequestsByService(Long serviceId);
    List<ChangeRequestResponse> getChangeRequestsByStatus(String status);
    ChangeRequestResponse updateChangeRequest(Long id, ChangeRequestRequest request);
    void deleteChangeRequest(Long id);
}
