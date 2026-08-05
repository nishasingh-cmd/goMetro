package com.gometro.service;

import com.gometro.entity.Ticket;
import java.util.List;

public interface TicketService {
    Ticket create(Ticket ticket);
    List<Ticket> getAll();
    Ticket getById(Long id);
    Ticket update(Long id, Ticket ticketDetails);
    void delete(Long id);
}
