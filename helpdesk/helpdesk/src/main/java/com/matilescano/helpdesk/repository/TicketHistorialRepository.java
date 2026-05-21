package com.matilescano.helpdesk.repository;

import com.matilescano.helpdesk.model.TicketHistorial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketHistorialRepository extends JpaRepository<TicketHistorial, Integer> {
    List<TicketHistorial> findByTicketIdTicketOrderByFechaCambioDesc(Integer idTicket);
}