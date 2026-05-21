package com.matilescano.helpdesk.controller;

import com.matilescano.helpdesk.model.RoleUsuario;
import com.matilescano.helpdesk.repository.RoleUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RoleUsuarioController {

    private final RoleUsuarioRepository roleUsuarioRepository;

    @GetMapping
    public List<RoleUsuario> getAll() {
        return roleUsuarioRepository.findAll();
    }

    @PostMapping
    public RoleUsuario create(@RequestBody RoleUsuario role) {
        return roleUsuarioRepository.save(role);
    }
}