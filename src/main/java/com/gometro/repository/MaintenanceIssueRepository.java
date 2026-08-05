package com.gometro.repository;

import com.gometro.entity.MaintenanceIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceIssueRepository extends JpaRepository<MaintenanceIssue, Long> {
    List<MaintenanceIssue> findByTrainId(Long trainId);
    List<MaintenanceIssue> findByStationId(Long stationId);
    List<MaintenanceIssue> findByReportedById(Long userId);
}
