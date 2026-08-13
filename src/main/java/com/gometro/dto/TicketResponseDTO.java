package com.gometro.dto;

import com.gometro.enums.TicketStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(name = "Ticket", description = "Ticket details including JPA entity relationships")
public class TicketResponseDTO {
    private Long id;
    private String ticketNumber;
    private String qrCodeData;
    private BigDecimal fareAmount;
    private TicketStatus status;
    private LocalDateTime issuedAt;
    private LocalDateTime validUntil;
    private LocalDateTime usedAt;

    private UserSummaryDTO user;
    private MetroCardSummaryDTO metroCard;
    private StationSummaryDTO sourceStation;
    private StationSummaryDTO destinationStation;
    private PaymentSummaryDTO payment;

    public TicketResponseDTO() {}

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
    public UserSummaryDTO getUser() { return user; }
    public void setUser(UserSummaryDTO user) { this.user = user; }
    public MetroCardSummaryDTO getMetroCard() { return metroCard; }
    public void setMetroCard(MetroCardSummaryDTO metroCard) { this.metroCard = metroCard; }
    public StationSummaryDTO getSourceStation() { return sourceStation; }
    public void setSourceStation(StationSummaryDTO sourceStation) { this.sourceStation = sourceStation; }
    public StationSummaryDTO getDestinationStation() { return destinationStation; }
    public void setDestinationStation(StationSummaryDTO destinationStation) { this.destinationStation = destinationStation; }
    public PaymentSummaryDTO getPayment() { return payment; }
    public void setPayment(PaymentSummaryDTO payment) { this.payment = payment; }
}
