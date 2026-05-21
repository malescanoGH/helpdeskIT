package com.matilescano.helpdesk.controller;

import com.matilescano.helpdesk.model.Board;
import com.matilescano.helpdesk.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/project/{idProject}")
    public List<Board> getByProject(@PathVariable Integer idProject) {
        return boardService.getBoardsByProject(idProject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getById(@PathVariable Integer id) {
        return boardService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Board create(@RequestBody Board board) {
        return boardService.save(board);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}