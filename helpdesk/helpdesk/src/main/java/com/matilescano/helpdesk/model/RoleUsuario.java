package com.matilescano.helpdesk.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role_usuario")
public class RoleUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "nombre_role", nullable = false, unique = true)
    private String nombreRole;
}