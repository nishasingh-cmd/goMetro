package com.gometro.dto;

import com.gometro.enums.MetroCardStatus;
import java.math.BigDecimal;

public class MetroCardSummaryDTO {
    private Long id;
    private String cardNumber;
    private BigDecimal balance;
    private MetroCardStatus status;

    public MetroCardSummaryDTO() {}

    public MetroCardSummaryDTO(Long id, String cardNumber, BigDecimal balance, MetroCardStatus status) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public MetroCardStatus getStatus() { return status; }
    public void setStatus(MetroCardStatus status) { this.status = status; }
}
