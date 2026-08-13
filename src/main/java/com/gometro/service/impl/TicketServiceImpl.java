package com.gometro.service.impl;

import com.gometro.dto.TicketResponseDTO;
import com.gometro.entity.Ticket;
import com.gometro.exception.ResourceNotFoundException;
import com.gometro.mapper.DtoMapper;
import com.gometro.repository.TicketRepository;
import com.gometro.service.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponseDTO getTicketResponseById(Long id) {
        Ticket ticket = getById(id);
        return DtoMapper.toTicketResponseDTO(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponseDTO> getAllTicketResponses() {
        return ticketRepository.findAll().stream()
                .map(DtoMapper::toTicketResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Ticket update(Long id, Ticket ticketDetails) {
        Ticket existingTicket = getById(id);
        if (ticketDetails.getStatus() != null) existingTicket.setStatus(ticketDetails.getStatus());
        if (ticketDetails.getFareAmount() != null) existingTicket.setFareAmount(ticketDetails.getFareAmount());
        if (ticketDetails.getValidUntil() != null) existingTicket.setValidUntil(ticketDetails.getValidUntil());
        if (ticketDetails.getUsedAt() != null) existingTicket.setUsedAt(ticketDetails.getUsedAt());
        return ticketRepository.save(existingTicket);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Ticket ticket = getById(id);
        ticketRepository.delete(ticket);
    }
}
