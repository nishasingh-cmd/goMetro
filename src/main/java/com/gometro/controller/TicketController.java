package com.gometro.controller;

import com.gometro.dto.TicketResponseDTO;
import com.gometro.entity.Ticket;
import com.gometro.response.ApiResponse;
import com.gometro.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TicketResponseDTO>> createTicket(@Valid @RequestBody Ticket ticket) {
        Ticket created = ticketService.create(ticket);
        TicketResponseDTO response = ticketService.getTicketResponseById(created.getId());
        return new ResponseEntity<>(ApiResponse.success("Ticket created successfully", response, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TicketResponseDTO>>> getAllTickets() {
        List<TicketResponseDTO> tickets = ticketService.getAllTicketResponses();
        return ResponseEntity.ok(ApiResponse.success("Tickets retrieved successfully", tickets));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketResponseDTO>> getTicketById(@PathVariable Long id) {
        TicketResponseDTO ticket = ticketService.getTicketResponseById(id);
        return ResponseEntity.ok(ApiResponse.success("Ticket retrieved successfully", ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketResponseDTO>> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        Ticket updated = ticketService.update(id, ticket);
        TicketResponseDTO response = ticketService.getTicketResponseById(updated.getId());
        return ResponseEntity.ok(ApiResponse.success("Ticket updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTicket(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Ticket deleted successfully", null));
    }
}
