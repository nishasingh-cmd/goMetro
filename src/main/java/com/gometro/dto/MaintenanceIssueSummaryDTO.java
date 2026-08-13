package com.gometro.dto;

import com.gometro.enums.MaintenancePriority;
import com.gometro.enums.MaintenanceStatus;
import com.gometro.enums.MaintenanceType;

public class MaintenanceIssueSummaryDTO {
    private Long id;
    private String title;
    private MaintenanceType issueType;
    private MaintenancePriority priority;
    private MaintenanceStatus status;

    public MaintenanceIssueSummaryDTO() {}

    public MaintenanceIssueSummaryDTO(Long id, String title, MaintenanceType issueType, MaintenancePriority priority, MaintenanceStatus status) {
        this.id = id;
        this.title = title;
        this.issueType = issueType;
        this.priority = priority;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public MaintenanceType getIssueType() { return issueType; }
    public void setIssueType(MaintenanceType issueType) { this.issueType = issueType; }
    public MaintenancePriority getPriority() { return priority; }
    public void setPriority(MaintenancePriority priority) { this.priority = priority; }
    public MaintenanceStatus getStatus() { return status; }
    public void setStatus(MaintenanceStatus status) { this.status = status; }
}
