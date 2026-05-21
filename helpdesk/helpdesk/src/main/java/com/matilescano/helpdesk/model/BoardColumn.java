package com.matilescano.helpdesk.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "board_column")
public class BoardColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_column")
    private Integer idColumn;

    @ManyToOne
    @JoinColumn(name = "id_board", nullable = false)
    private Board board;

    @Column(nullable = false)
    private String nombre;

    private Integer posicion;
}