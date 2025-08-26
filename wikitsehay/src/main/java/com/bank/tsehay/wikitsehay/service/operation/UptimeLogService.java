package com.bank.tsehay.wikitsehay.service.operation;

import com.bank.tsehay.wikitsehay.dto.operation.UptimeLogRequest;
import com.bank.tsehay.wikitsehay.dto.operation.UptimeLogResponse;

import java.util.List;

public interface UptimeLogService {
    UptimeLogResponse createUptimeLog(UptimeLogRequest request);
    UptimeLogResponse getUptimeLog(Long id);
    List<UptimeLogResponse> getAllUptimeLogs();
    List<UptimeLogResponse> getLogsByService(Long serviceId);
    List<UptimeLogResponse> getLogsByPeriod(String periodType);
    UptimeLogResponse updateUptimeLog(Long id, UptimeLogRequest request);
    void deleteUptimeLog(Long id);
}

