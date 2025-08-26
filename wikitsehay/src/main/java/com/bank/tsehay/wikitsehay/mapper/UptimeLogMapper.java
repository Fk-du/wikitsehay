package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.operation.UptimeLogRequest;
import com.bank.tsehay.wikitsehay.dto.operation.UptimeLogResponse;
import com.bank.tsehay.wikitsehay.model.operation.Operation;
import com.bank.tsehay.wikitsehay.model.operation.ReleaseDeployment;
import com.bank.tsehay.wikitsehay.model.operation.UptimeLog;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UptimeLogMapper {

    public UptimeLog toEntity(
            UptimeLogRequest request,
            Operation service,
            ReleaseDeployment releaseDeployment
    ) {
        UptimeLog log = new UptimeLog();
        log.setLogDate(LocalDateTime.now()); // auto-set
        log.setUptimePercentage(request.getUptimePercentage());
        log.setPeriodType(request.getPeriodType());
        log.setService(service);
        log.setReleaseDeployment(releaseDeployment);
        return log;
    }

    public UptimeLogResponse toResponse(UptimeLog log) {
        UptimeLogResponse response = new UptimeLogResponse();
        response.setId(log.getId());
        response.setLogDate(log.getLogDate());
        response.setUptimePercentage(log.getUptimePercentage());
        response.setPeriodType(log.getPeriodType());
        response.setServiceId(log.getService().getId());
        response.setReleaseDeploymentId(
                log.getReleaseDeployment() != null ? log.getReleaseDeployment().getId() : null
        );
        return response;
    }
}

