package com.gometro.controller;

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
    public ResponseEntity<ApiResponse<Ticket>> createTicket(@Valid @RequestBody Ticket ticket) {
        Ticket created = ticketService.create(ticket);
        return new ResponseEntity<>(ApiResponse.success("Ticket created successfully", created, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Ticket>>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Tickets retrieved successfully", tickets));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Ticket>> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Ticket retrieved successfully", ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Ticket>> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        Ticket updated = ticketService.update(id, ticket);
        return ResponseEntity.ok(ApiResponse.success("Ticket updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTicket(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Ticket deleted successfully", null));
    }
}
