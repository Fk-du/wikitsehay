package com.bank.tsehay.wikitsehay.repository.operation;

import com.bank.tsehay.wikitsehay.model.operation.UptimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UptimeLogRepository extends JpaRepository<UptimeLog, Long> {
    List<UptimeLog> findByServiceId(Long serviceId);
    List<UptimeLog> findByPeriodType(String periodType);
}
