package com.gometro.service;

import com.gometro.dto.TicketResponseDTO;
import com.gometro.entity.Ticket;
import java.util.List;

public interface TicketService {
    Ticket create(Ticket ticket);
    List<Ticket> getAll();
    Ticket getById(Long id);
    TicketResponseDTO getTicketResponseById(Long id);
    List<TicketResponseDTO> getAllTicketResponses();
    Ticket update(Long id, Ticket ticketDetails);
    void delete(Long id);
}
