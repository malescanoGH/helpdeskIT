package com.matilescano.helpdesk.service;

import com.matilescano.helpdesk.model.Project;
import com.matilescano.helpdesk.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByUser(Integer idUser) {
        return projectRepository.findByOwnerIdUser(idUser);
    }

    public Optional<Project> getById(Integer id) {
        return projectRepository.findById(id);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public void delete(Integer id) {
        projectRepository.deleteById(id);
    }
}