package com.bank.tsehay.wikitsehay.service.operation;

import com.bank.tsehay.wikitsehay.dto.operation.UptimeLogRequest;
import com.bank.tsehay.wikitsehay.dto.operation.UptimeLogResponse;
import com.bank.tsehay.wikitsehay.mapper.UptimeLogMapper;
import com.bank.tsehay.wikitsehay.model.operation.Operation;
import com.bank.tsehay.wikitsehay.model.operation.ReleaseDeployment;
import com.bank.tsehay.wikitsehay.model.operation.UptimeLog;
import com.bank.tsehay.wikitsehay.repository.operation.OperationRepository;
import com.bank.tsehay.wikitsehay.repository.operation.ReleaseDeploymentRepository;
import com.bank.tsehay.wikitsehay.repository.operation.UptimeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UptimeLogServiceImpl implements UptimeLogService {

    private final UptimeLogRepository uptimeLogRepository;
    private final OperationRepository operationRepository;
    private final ReleaseDeploymentRepository releaseDeploymentRepository;
    private final UptimeLogMapper mapper;

    @Override
    public UptimeLogResponse createUptimeLog(UptimeLogRequest request) {
        Operation service = operationRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        ReleaseDeployment releaseDeployment = request.getReleaseDeploymentId() != null ?
                releaseDeploymentRepository.findById(request.getReleaseDeploymentId())
                        .orElseThrow(() -> new RuntimeException("ReleaseDeployment not found"))
                : null;

        UptimeLog log = mapper.toEntity(request, service, releaseDeployment);
        return mapper.toResponse(uptimeLogRepository.save(log));
    }

    @Override
    public UptimeLogResponse getUptimeLog(Long id) {
        return uptimeLogRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("UptimeLog not found"));
    }

    @Override
    public List<UptimeLogResponse> getAllUptimeLogs() {
        return uptimeLogRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<UptimeLogResponse> getLogsByService(Long serviceId) {
        return uptimeLogRepository.findByServiceId(serviceId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<UptimeLogResponse> getLogsByPeriod(String periodType) {
        return uptimeLogRepository.findByPeriodType(periodType).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public UptimeLogResponse updateUptimeLog(Long id, UptimeLogRequest request) {
        UptimeLog log = uptimeLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UptimeLog not found"));

        Operation service = operationRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        ReleaseDeployment releaseDeployment = request.getReleaseDeploymentId() != null ?
                releaseDeploymentRepository.findById(request.getReleaseDeploymentId())
                        .orElseThrow(() -> new RuntimeException("ReleaseDeployment not found"))
                : null;

        log.setUptimePercentage(request.getUptimePercentage());
        log.setPeriodType(request.getPeriodType());
        log.setService(service);
        log.setReleaseDeployment(releaseDeployment);

        return mapper.toResponse(uptimeLogRepository.save(log));
    }

    @Override
    public void deleteUptimeLog(Long id) {
        uptimeLogRepository.deleteById(id);
    }
}
