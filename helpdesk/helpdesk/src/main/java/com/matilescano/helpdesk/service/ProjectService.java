package com.matilescano.helpdesk.service;

import com.matilescano.helpdesk.model.Board;
import com.matilescano.helpdesk.model.BoardColumn;
import com.matilescano.helpdesk.model.Project;
import com.matilescano.helpdesk.repository.BoardColumnRepository;
import com.matilescano.helpdesk.repository.BoardRepository;
import com.matilescano.helpdesk.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final BoardRepository boardRepository;
    private final BoardColumnRepository boardColumnRepository;

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
        Project savedProject = projectRepository.save(project);
        crearBoardPorDefecto(savedProject);
        return savedProject;
    }

    private void crearBoardPorDefecto(Project project) {
        Board board = new Board();
        board.setNombre("Tablero Principal");
        board.setTipo("KANBAN");
        board.setProject(project);
        Board savedBoard = boardRepository.save(board);

        String[] columnasDefault = {"Por hacer", "En progreso", "En revisión", "Hecho"};
        for (int i = 0; i < columnasDefault.length; i++) {
            BoardColumn col = new BoardColumn();
            col.setNombre(columnasDefault[i]);
            col.setPosicion(i);
            col.setBoard(savedBoard);
            boardColumnRepository.save(col);
        }
    }

    public void delete(Integer id) {
        projectRepository.deleteById(id);
    }
}