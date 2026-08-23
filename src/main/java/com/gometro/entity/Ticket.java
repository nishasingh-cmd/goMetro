package com.gometro.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gometro.enums.TicketStatus;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "ticket_number", unique = true, nullable = false)
    private String ticketNumber;

    @Column(name = "qr_code_data", unique = true)
    private String qrCodeData;

    @NotNull
    @PositiveOrZero
    @Column(name = "fare_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal fareAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    @Column(name = "issued_at", nullable = false, updatable = false)
    private LocalDateTime issuedAt;

    @Column(name = "valid_until")
    private LocalDateTime validUntil;

    @Column(name = "used_at")
    private LocalDateTime usedAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"metroCards", "tickets", "reportedIssues"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metro_card_id")
    @JsonIgnoreProperties({"tickets", "payments"})
    private MetroCard metroCard;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_station_id", nullable = false)
    @JsonIgnoreProperties({"sourceTickets", "destinationTickets", "routeStations", "departureSchedules", "arrivalSchedules", "originFares", "destinationFares"})
    private Station sourceStation;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_station_id", nullable = false)
    @JsonIgnoreProperties({"sourceTickets", "destinationTickets", "routeStations", "departureSchedules", "arrivalSchedules", "originFares", "destinationFares"})
    private Station destinationStation;

    @OneToOne(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"ticket"})
    private Payment payment;

    @PrePersist
    protected void onCreate() {
        this.issuedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(String ticketNumber) { this.ticketNumber = ticketNumber; }
    public String getQrCodeData() { return qrCodeData; }
    public void setQrCodeData(String qrCodeData) { this.qrCodeData = qrCodeData; }
    public BigDecimal getFareAmount() { return fareAmount; }
    public void setFareAmount(BigDecimal fareAmount) { this.fareAmount = fareAmount; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
    public LocalDateTime getValidUntil() { return validUntil; }
    public void setValidUntil(LocalDateTime validUntil) { this.validUntil = validUntil; }
    public LocalDateTime getUsedAt() { return usedAt; }
    public void setUsedAt(LocalDateTime usedAt) { this.usedAt = usedAt; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public MetroCard getMetroCard() { return metroCard; }
    public void setMetroCard(MetroCard metroCard) { this.metroCard = metroCard; }
    public Station getSourceStation() { return sourceStation; }
    public void setSourceStation(Station sourceStation) { this.sourceStation = sourceStation; }
    public Station getDestinationStation() { return destinationStation; }
    public void setDestinationStation(Station destinationStation) { this.destinationStation = destinationStation; }
    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }
}
