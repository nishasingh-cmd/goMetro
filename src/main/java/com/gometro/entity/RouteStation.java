package com.gometro.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "route_stations",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"route_id", "station_sequence"}),
        @UniqueConstraint(columnNames = {"route_id", "station_id"})
    }
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RouteStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "station_sequence", nullable = false)
    private Integer stationSequence;

    @NotNull
    @PositiveOrZero
    @Column(name = "distance_from_source_km", nullable = false, precision = 8, scale = 2)
    private BigDecimal distanceFromSourceKm;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    @JsonIgnoreProperties({"routeStations", "trains", "schedules"})
    private Route route;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", nullable = false)
    @JsonIgnoreProperties({"routeStations", "departureSchedules", "arrivalSchedules", "originFares", "destinationFares", "sourceTickets", "destinationTickets"})
    private Station station;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getStationSequence() { return stationSequence; }
    public void setStationSequence(Integer stationSequence) { this.stationSequence = stationSequence; }
    public BigDecimal getDistanceFromSourceKm() { return distanceFromSourceKm; }
    public void setDistanceFromSourceKm(BigDecimal distanceFromSourceKm) { this.distanceFromSourceKm = distanceFromSourceKm; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }
    public Station getStation() { return station; }
    public void setStation(Station station) { this.station = station; }
}
