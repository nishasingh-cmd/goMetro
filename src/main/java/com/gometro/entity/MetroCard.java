package com.gometro.entity;

import com.gometro.enums.MetroCardStatus;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "metro_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetroCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "card_number", unique = true, nullable = false)
    private String cardNumber;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetroCardStatus status;

    @Column(name = "issued_at", nullable = false, updatable = false)
    private LocalDateTime issuedAt;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "metroCard", fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "metroCard", fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.issuedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
