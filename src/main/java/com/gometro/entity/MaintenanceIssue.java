package com.gometro.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gometro.enums.MaintenancePriority;
import com.gometro.enums.MaintenanceStatus;
import com.gometro.enums.MaintenanceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_issues")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MaintenanceIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "issue_type", nullable = false)
    private MaintenanceType issueType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaintenancePriority priority;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaintenanceStatus status;

    @Column(name = "reported_at", nullable = false, updatable = false)
    private LocalDateTime reportedAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id")
    @JsonIgnoreProperties({"maintenanceIssues", "schedules"})
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    @JsonIgnoreProperties({"routeStations", "departureSchedules", "arrivalSchedules", "originFares", "destinationFares", "sourceTickets", "destinationTickets"})
    private Station station;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_by_user_id", nullable = false)
    @JsonIgnoreProperties({"metroCards", "tickets", "reportedIssues"})
    private User reportedBy;

    @PrePersist
    protected void onCreate() {
        this.reportedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public MaintenanceType getIssueType() { return issueType; }
    public void setIssueType(MaintenanceType issueType) { this.issueType = issueType; }
    public MaintenancePriority getPriority() { return priority; }
    public void setPriority(MaintenancePriority priority) { this.priority = priority; }
    public MaintenanceStatus getStatus() { return status; }
    public void setStatus(MaintenanceStatus status) { this.status = status; }
    public LocalDateTime getReportedAt() { return reportedAt; }
    public void setReportedAt(LocalDateTime reportedAt) { this.reportedAt = reportedAt; }
    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }
    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }
    public Station getStation() { return station; }
    public void setStation(Station station) { this.station = station; }
    public User getReportedBy() { return reportedBy; }
    public void setReportedBy(User reportedBy) { this.reportedBy = reportedBy; }
}
