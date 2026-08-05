package com.gometro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stations")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "station_code", unique = true, nullable = false)
    private String stationCode;

    @NotBlank
    @Column(name = "station_name", nullable = false)
    private String stationName;

    @NotBlank
    @Column(nullable = false)
    private String location;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "station", fetch = FetchType.LAZY)
    private List<RouteStation> routeStations = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "sourceStation", fetch = FetchType.LAZY)
    private List<Schedule> departureSchedules = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "destinationStation", fetch = FetchType.LAZY)
    private List<Schedule> arrivalSchedules = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "sourceStation", fetch = FetchType.LAZY)
    private List<Fare> originFares = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "destinationStation", fetch = FetchType.LAZY)
    private List<Fare> destinationFares = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "sourceStation", fetch = FetchType.LAZY)
    private List<Ticket> sourceTickets = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "destinationStation", fetch = FetchType.LAZY)
    private List<Ticket> destinationTickets = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStationCode() { return stationCode; }
    public void setStationCode(String stationCode) { this.stationCode = stationCode; }
    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public List<RouteStation> getRouteStations() { return routeStations; }
    public void setRouteStations(List<RouteStation> routeStations) { this.routeStations = routeStations; }
    public List<Schedule> getDepartureSchedules() { return departureSchedules; }
    public void setDepartureSchedules(List<Schedule> departureSchedules) { this.departureSchedules = departureSchedules; }
    public List<Schedule> getArrivalSchedules() { return arrivalSchedules; }
    public void setArrivalSchedules(List<Schedule> arrivalSchedules) { this.arrivalSchedules = arrivalSchedules; }
    public List<Fare> getOriginFares() { return originFares; }
    public void setOriginFares(List<Fare> originFares) { this.originFares = originFares; }
    public List<Fare> getDestinationFares() { return destinationFares; }
    public void setDestinationFares(List<Fare> destinationFares) { this.destinationFares = destinationFares; }
    public List<Ticket> getSourceTickets() { return sourceTickets; }
    public void setSourceTickets(List<Ticket> sourceTickets) { this.sourceTickets = sourceTickets; }
    public List<Ticket> getDestinationTickets() { return destinationTickets; }
    public void setDestinationTickets(List<Ticket> destinationTickets) { this.destinationTickets = destinationTickets; }
}
