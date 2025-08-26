package com.bank.tsehay.wikitsehay.service.operation;

import com.bank.tsehay.wikitsehay.dto.operation.ChangeRequestRequest;
import com.bank.tsehay.wikitsehay.dto.operation.ChangeRequestResponse;
import com.bank.tsehay.wikitsehay.mapper.ChangeRequestMapper;
import com.bank.tsehay.wikitsehay.model.operation.ChangeRequest;
import com.bank.tsehay.wikitsehay.model.operation.Operation;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.repository.UserRepository;
import com.bank.tsehay.wikitsehay.repository.operation.ChangeRequestRepository;
import com.bank.tsehay.wikitsehay.repository.operation.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChangeRequestServiceImpl implements ChangeRequestService {

    private final ChangeRequestRepository changeRequestRepository;
    private final OperationRepository operationRepository;
    private final UserRepository userRepository;
    private final ChangeRequestMapper mapper;

    @Override
    public ChangeRequestResponse createChangeRequest(ChangeRequestRequest request) {
        Operation service = operationRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        User requester = request.getRequesterId() != null ?
                userRepository.findById(request.getRequesterId())
                        .orElseThrow(() -> new RuntimeException("Requester not found"))
                : null;

        User approvedBy = request.getApprovedById() != null ?
                userRepository.findById(request.getApprovedById())
                        .orElseThrow(() -> new RuntimeException("ApprovedBy not found"))
                : null;

        User implementedBy = request.getImplementedById() != null ?
                userRepository.findById(request.getImplementedById())
                        .orElseThrow(() -> new RuntimeException("ImplementedBy not found"))
                : null;

        ChangeRequest changeRequest = mapper.toEntity(request, service, requester, approvedBy, implementedBy);
        return mapper.toResponse(changeRequestRepository.save(changeRequest));
    }

    @Override
    public ChangeRequestResponse getChangeRequest(Long id) {
        return changeRequestRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("ChangeRequest not found"));
    }

    @Override
    public List<ChangeRequestResponse> getAllChangeRequests() {
        return changeRequestRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ChangeRequestResponse> getChangeRequestsByService(Long serviceId) {
        return changeRequestRepository.findByServiceId(serviceId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ChangeRequestResponse> getChangeRequestsByStatus(String status) {
        return changeRequestRepository.findByApprovalStatus(status).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ChangeRequestResponse updateChangeRequest(Long id, ChangeRequestRequest request) {
        ChangeRequest changeRequest = changeRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChangeRequest not found"));

        Operation service = operationRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        User requester = request.getRequesterId() != null ?
                userRepository.findById(request.getRequesterId())
                        .orElseThrow(() -> new RuntimeException("Requester not found"))
                : null;

        User approvedBy = request.getApprovedById() != null ?
                userRepository.findById(request.getApprovedById())
                        .orElseThrow(() -> new RuntimeException("ApprovedBy not found"))
                : null;

        User implementedBy = request.getImplementedById() != null ?
                userRepository.findById(request.getImplementedById())
                        .orElseThrow(() -> new RuntimeException("ImplementedBy not found"))
                : null;

        changeRequest.setType(request.getType());
        changeRequest.setApprovalStatus(request.getApprovalStatus());
        changeRequest.setImplementationDate(request.getImplementationDate());
        changeRequest.setService(service);
        changeRequest.setRequester(requester);
        changeRequest.setApprovedBy(approvedBy);
        changeRequest.setImplementedBy(implementedBy);

        return mapper.toResponse(changeRequestRepository.save(changeRequest));
    }

    @Override
    public void deleteChangeRequest(Long id) {
        changeRequestRepository.deleteById(id);
    }
}
