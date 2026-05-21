package com.matilescano.helpdesk.repository;

import com.matilescano.helpdesk.model.RoleUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUsuarioRepository extends JpaRepository<RoleUsuario, Integer> {
}