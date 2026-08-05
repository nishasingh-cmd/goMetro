package com.gometro.service;

import com.gometro.entity.MaintenanceIssue;
import java.util.List;

public interface MaintenanceIssueService {
    MaintenanceIssue create(MaintenanceIssue maintenanceIssue);
    List<MaintenanceIssue> getAll();
    MaintenanceIssue getById(Long id);
    MaintenanceIssue update(Long id, MaintenanceIssue issueDetails);
    void delete(Long id);
}
