package com.matilescano.helpdesk.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Integer idTicket;

    @ManyToOne
    @JoinColumn(name = "id_column", nullable = false)
    private BoardColumn column;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Usuario creadoPor;

    @ManyToOne
    @JoinColumn(name = "id_assigned")
    private Usuario asignadoA;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private String estado; // "TODO", "IN_PROGRESS", "DONE"

    @Column(nullable = false)
    private String prioridad; // "ALTA", "MEDIA", "BAJA"

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToMany
    @JoinTable(
            name = "ticket_label",
            joinColumns = @JoinColumn(name = "id_ticket"),
            inverseJoinColumns = @JoinColumn(name = "id_label")
    )
    private List<Label> labels;
}