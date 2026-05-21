package com.matilescano.helpdesk.service;

import com.matilescano.helpdesk.model.Ticket;
import com.matilescano.helpdesk.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public List<Ticket> getTicketsByColumn(Integer idColumn) {
        return ticketRepository.findByColumnIdColumn(idColumn);
    }

    public List<Ticket> getTicketsByAsignado(Integer idUser) {
        return ticketRepository.findByAsignadoAIdUser(idUser);
    }

    public Optional<Ticket> getById(Integer id) {
        return ticketRepository.findById(id);
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void delete(Integer id) {
        ticketRepository.deleteById(id);
    }
}