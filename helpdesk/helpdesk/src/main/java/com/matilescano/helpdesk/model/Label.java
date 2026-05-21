package com.matilescano.helpdesk.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "label")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_label")
    private Integer idLabel;

    @ManyToOne
    @JoinColumn(name = "id_project", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String nombre;

    private String color; // ej: "#FF5733"
}