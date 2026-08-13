package com.gometro.dto;

import com.gometro.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Schema(name = "User", description = "User details including JPA entity relationships")
public class UserResponseDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserRole role;
    private Boolean active;

    private MetroCardSummaryDTO metroCard;
    private List<TicketSummaryDTO> tickets = new ArrayList<>();
    private List<MaintenanceIssueSummaryDTO> reportedIssues = new ArrayList<>();

    private LocalDateTime registrationDate;
    private LocalDateTime updatedAt;

    public UserResponseDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public MetroCardSummaryDTO getMetroCard() { return metroCard; }
    public void setMetroCard(MetroCardSummaryDTO metroCard) { this.metroCard = metroCard; }
    public List<TicketSummaryDTO> getTickets() { return tickets; }
    public void setTickets(List<TicketSummaryDTO> tickets) { this.tickets = tickets; }
    public List<MaintenanceIssueSummaryDTO> getReportedIssues() { return reportedIssues; }
    public void setReportedIssues(List<MaintenanceIssueSummaryDTO> reportedIssues) { this.reportedIssues = reportedIssues; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
