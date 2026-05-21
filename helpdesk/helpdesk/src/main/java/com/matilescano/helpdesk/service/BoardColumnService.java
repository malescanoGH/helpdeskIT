package com.matilescano.helpdesk.service;

import com.matilescano.helpdesk.model.BoardColumn;
import com.matilescano.helpdesk.repository.BoardColumnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardColumnService {

    private final BoardColumnRepository boardColumnRepository;

    public List<BoardColumn> getColumnsByBoard(Integer idBoard) {
        return boardColumnRepository.findByBoardIdBoardOrderByPosicion(idBoard);
    }

    public Optional<BoardColumn> getById(Integer id) {
        return boardColumnRepository.findById(id);
    }

    public BoardColumn save(BoardColumn column) {
        return boardColumnRepository.save(column);
    }

    public void delete(Integer id) {
        boardColumnRepository.deleteById(id);
    }
}