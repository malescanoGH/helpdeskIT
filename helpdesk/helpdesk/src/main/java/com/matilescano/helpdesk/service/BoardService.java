package com.matilescano.helpdesk.service;

import com.matilescano.helpdesk.model.Board;
import com.matilescano.helpdesk.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getBoardsByProject(Integer idProject) {
        return boardRepository.findByProjectIdProject(idProject);
    }

    public Optional<Board> getById(Integer id) {
        return boardRepository.findById(id);
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public void delete(Integer id) {
        boardRepository.deleteById(id);
    }
}