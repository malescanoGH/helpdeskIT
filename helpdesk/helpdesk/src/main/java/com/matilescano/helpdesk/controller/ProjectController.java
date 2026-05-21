package com.matilescano.helpdesk.controller;

import com.matilescano.helpdesk.model.Project;
import com.matilescano.helpdesk.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAll() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Integer id) {
        return projectService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{idUser}")
    public List<Project> getByUser(@PathVariable Integer idUser) {
        return projectService.getProjectsByUser(idUser);
    }

    @PostMapping
    public Project create(@RequestBody Project project) {
        return projectService.save(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable Integer id, @RequestBody Project project) {
        return projectService.getById(id).map(existing -> {
            existing.setNombre(project.getNombre());
            existing.setDescripcion(project.getDescripcion());
            return ResponseEntity.ok(projectService.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}