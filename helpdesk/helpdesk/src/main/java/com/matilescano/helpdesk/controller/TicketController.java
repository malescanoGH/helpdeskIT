package com.matilescano.helpdesk.controller;

import com.matilescano.helpdesk.model.Ticket;
import com.matilescano.helpdesk.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/column/{idColumn}")
    public List<Ticket> getByColumn(@PathVariable Integer idColumn) {
        return ticketService.getTicketsByColumn(idColumn);
    }

    @GetMapping("/asignado/{idUser}")
    public List<Ticket> getByAsignado(@PathVariable Integer idUser) {
        return ticketService.getTicketsByAsignado(idUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable Integer id) {
        return ticketService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ticket create(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Integer id, @RequestBody Ticket ticket) {
        return ticketService.getById(id).map(existing -> {
            existing.setTitulo(ticket.getTitulo());
            existing.setDescripcion(ticket.getDescripcion());
            existing.setEstado(ticket.getEstado());
            existing.setPrioridad(ticket.getPrioridad());
            existing.setDueDate(ticket.getDueDate());
            existing.setColumn(ticket.getColumn());
            existing.setAsignadoA(ticket.getAsignadoA());
            return ResponseEntity.ok(ticketService.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}