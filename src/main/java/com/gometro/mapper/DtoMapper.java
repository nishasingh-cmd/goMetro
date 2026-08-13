package com.gometro.mapper;

import com.gometro.dto.MaintenanceIssueSummaryDTO;
import com.gometro.dto.MetroCardSummaryDTO;
import com.gometro.dto.PaymentSummaryDTO;
import com.gometro.dto.StationSummaryDTO;
import com.gometro.dto.TicketResponseDTO;
import com.gometro.dto.TicketSummaryDTO;
import com.gometro.dto.UserRequestDTO;
import com.gometro.dto.UserResponseDTO;
import com.gometro.dto.UserSummaryDTO;
import com.gometro.entity.MaintenanceIssue;
import com.gometro.entity.MetroCard;
import com.gometro.entity.Payment;
import com.gometro.entity.Station;
import com.gometro.entity.Ticket;
import com.gometro.entity.User;

import java.util.Collections;
import java.util.stream.Collectors;

public class DtoMapper {

    public static User toUserEntity(UserRequestDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(dto.getRole());
        if (dto.getActive() != null) {
            user.setActive(dto.getActive());
        }
        return user;
    }

    public static UserSummaryDTO toUserSummaryDTO(User user) {
        if (user == null) return null;
        return new UserSummaryDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    public static MetroCardSummaryDTO toMetroCardSummaryDTO(MetroCard card) {
        if (card == null) return null;
        return new MetroCardSummaryDTO(
                card.getId(),
                card.getCardNumber(),
                card.getBalance(),
                card.getStatus()
        );
    }

    public static TicketSummaryDTO toTicketSummaryDTO(Ticket ticket) {
        if (ticket == null) return null;
        return new TicketSummaryDTO(
                ticket.getId(),
                ticket.getTicketNumber(),
                ticket.getFareAmount(),
                ticket.getStatus()
        );
    }

    public static StationSummaryDTO toStationSummaryDTO(Station station) {
        if (station == null) return null;
        return new StationSummaryDTO(
                station.getId(),
                station.getStationCode(),
                station.getStationName()
        );
    }

    public static PaymentSummaryDTO toPaymentSummaryDTO(Payment payment) {
        if (payment == null) return null;
        return new PaymentSummaryDTO(
                payment.getId(),
                payment.getTransactionId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getPaymentTime()
        );
    }

    public static MaintenanceIssueSummaryDTO toMaintenanceIssueSummaryDTO(MaintenanceIssue issue) {
        if (issue == null) return null;
        return new MaintenanceIssueSummaryDTO(
                issue.getId(),
                issue.getTitle(),
                issue.getIssueType(),
                issue.getPriority(),
                issue.getStatus()
        );
    }

    public static UserResponseDTO toUserResponseDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());
        dto.setActive(user.getActive());
        dto.setMetroCard(toMetroCardSummaryDTO(user.getMetroCard()));
        dto.setTickets(user.getTickets() == null ? Collections.emptyList() :
                user.getTickets().stream().map(DtoMapper::toTicketSummaryDTO).collect(Collectors.toList()));
        dto.setReportedIssues(user.getReportedIssues() == null ? Collections.emptyList() :
                user.getReportedIssues().stream().map(DtoMapper::toMaintenanceIssueSummaryDTO).collect(Collectors.toList()));
        dto.setRegistrationDate(user.getRegistrationDate());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    public static TicketResponseDTO toTicketResponseDTO(Ticket ticket) {
        if (ticket == null) return null;
        TicketResponseDTO dto = new TicketResponseDTO();
        dto.setId(ticket.getId());
        dto.setTicketNumber(ticket.getTicketNumber());
        dto.setQrCodeData(ticket.getQrCodeData());
        dto.setFareAmount(ticket.getFareAmount());
        dto.setStatus(ticket.getStatus());
        dto.setIssuedAt(ticket.getIssuedAt());
        dto.setValidUntil(ticket.getValidUntil());
        dto.setUsedAt(ticket.getUsedAt());
        dto.setUser(toUserSummaryDTO(ticket.getUser()));
        dto.setMetroCard(toMetroCardSummaryDTO(ticket.getMetroCard()));
        dto.setSourceStation(toStationSummaryDTO(ticket.getSourceStation()));
        dto.setDestinationStation(toStationSummaryDTO(ticket.getDestinationStation()));
        dto.setPayment(toPaymentSummaryDTO(ticket.getPayment()));
        return dto;
    }
}
