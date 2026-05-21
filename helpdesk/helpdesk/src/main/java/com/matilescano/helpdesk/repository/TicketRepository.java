package com.matilescano.helpdesk.repository;

import com.matilescano.helpdesk.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByColumnIdColumn(Integer idColumn);
    List<Ticket> findByAsignadoAIdUser(Integer idUser);
}