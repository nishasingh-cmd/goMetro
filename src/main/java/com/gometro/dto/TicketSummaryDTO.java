package com.gometro.dto;

import com.gometro.enums.TicketStatus;
import java.math.BigDecimal;

public class TicketSummaryDTO {
    private Long id;
    private String ticketNumber;
    private BigDecimal fareAmount;
    private TicketStatus status;

    public TicketSummaryDTO() {}

    public TicketSummaryDTO(Long id, String ticketNumber, BigDecimal fareAmount, TicketStatus status) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.fareAmount = fareAmount;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(String ticketNumber) { this.ticketNumber = ticketNumber; }
    public BigDecimal getFareAmount() { return fareAmount; }
    public void setFareAmount(BigDecimal fareAmount) { this.fareAmount = fareAmount; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
}
