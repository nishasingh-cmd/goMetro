package com.gometro.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "\"User\"")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {

    @Id
    @Column(name = "\"UserId\"", length = 50, nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Auto-generated user ID")
    private String userId;

    @NotBlank(message = "First name is required")
    @Column(name = "\"FirstName\"", length = 100, nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "\"LastName\"", length = 100, nullable = false)
    private String lastName;

    @NotBlank(message = "Password is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "\"Password\"", length = 255, nullable = false)
    private String password;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "\"Role\"", length = 50, nullable = false)
    private Role role;

    @NotBlank(message = "Contact number is required")
    @Column(name = "\"Contact\"", length = 50)
    private String contact;

    @Column(name = "\"RegisterationDate\"", nullable = false, updatable = false)
    private LocalDate registrationDate;

    @Column(name = "\"isActive\"", nullable = false)
    @JsonProperty("isActive")
    private Boolean isActive;

    @Column(name = "\"HireDate\"")
    private LocalDate hireDate;

    @Column(name = "\"StationID\"", length = 50)
    private String stationId;

    public User() {
    }

    public User(String userId, String firstName, String lastName, String password, Role role,
            String contact, LocalDate registrationDate, Boolean isActive, LocalDate hireDate, String stationId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.contact = contact;
        this.registrationDate = registrationDate;
        this.isActive = isActive;
        this.hireDate = hireDate;
        this.stationId = stationId;
    }

    @PrePersist
    protected void onCreate() {
        if (this.userId == null && this.role != null) {
            String prefix = switch (this.role) {
                case PASSENGER -> "PASS";
                case ADMIN -> "ADMIN";
                case TICKET_AGENT, MAINTENANCE_STAFF -> "EMP";
            };
            this.userId = prefix + "-" + UUID.randomUUID().toString();
        }
        if (this.registrationDate == null) {
            this.registrationDate = LocalDate.now();
        }
        if (this.isActive == null) {
            this.isActive = true;
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
}