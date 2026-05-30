package com.matilescano.helpdesk.service;

import com.matilescano.helpdesk.model.Usuario;
import com.matilescano.helpdesk.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public boolean verificarPassword(String passwordPlana, String passwordEncriptada) {
        return passwordEncoder.matches(passwordPlana, passwordEncriptada);
    }

    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }
}