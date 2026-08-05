package com.gometro.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stations")
@Getter
@Setter
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

    @Builder.Default
    @OneToMany(mappedBy = "station", fetch = FetchType.LAZY)
    private List<RouteStation> routeStations = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "sourceStation", fetch = FetchType.LAZY)
    private List<Schedule> departureSchedules = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "destinationStation", fetch = FetchType.LAZY)
    private List<Schedule> arrivalSchedules = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "sourceStation", fetch = FetchType.LAZY)
    private List<Fare> originFares = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "destinationStation", fetch = FetchType.LAZY)
    private List<Fare> destinationFares = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "sourceStation", fetch = FetchType.LAZY)
    private List<Ticket> sourceTickets = new ArrayList<>();

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
}
