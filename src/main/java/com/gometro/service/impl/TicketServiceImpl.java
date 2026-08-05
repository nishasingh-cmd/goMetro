package com.gometro.service.impl;

import com.gometro.entity.Ticket;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.repository.TicketRepository;
import com.gometro.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
    }

    @Override
    public Ticket update(Long id, Ticket ticketDetails) {
        Ticket existingTicket = getById(id);
        if (ticketDetails.getStatus() != null) existingTicket.setStatus(ticketDetails.getStatus());
        if (ticketDetails.getFareAmount() != null) existingTicket.setFareAmount(ticketDetails.getFareAmount());
        if (ticketDetails.getValidUntil() != null) existingTicket.setValidUntil(ticketDetails.getValidUntil());
        if (ticketDetails.getUsedAt() != null) existingTicket.setUsedAt(ticketDetails.getUsedAt());
        return ticketRepository.save(existingTicket);
    }

    @Override
    public void delete(Long id) {
        Ticket ticket = getById(id);
        ticketRepository.delete(ticket);
    }
}
