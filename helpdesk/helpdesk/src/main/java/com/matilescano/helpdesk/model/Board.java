package com.matilescano.helpdesk.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_board")
    private Integer idBoard;

    @ManyToOne
    @JoinColumn(name = "id_project", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo; // "KANBAN" o "SCRUM"
}