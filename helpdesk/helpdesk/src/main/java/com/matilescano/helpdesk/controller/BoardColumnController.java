package com.matilescano.helpdesk.controller;

import com.matilescano.helpdesk.model.BoardColumn;
import com.matilescano.helpdesk.service.BoardColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/columns")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BoardColumnController {

    private final BoardColumnService boardColumnService;

    @GetMapping("/board/{idBoard}")
    public List<BoardColumn> getByBoard(@PathVariable Integer idBoard) {
        return boardColumnService.getColumnsByBoard(idBoard);
    }

    @PostMapping
    public BoardColumn create(@RequestBody BoardColumn column) {
        return boardColumnService.save(column);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardColumn> update(@PathVariable Integer id, @RequestBody BoardColumn column) {
        return boardColumnService.getById(id).map(existing -> {
            existing.setNombre(column.getNombre());
            existing.setPosicion(column.getPosicion());
            return ResponseEntity.ok(boardColumnService.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boardColumnService.delete(id);
        return ResponseEntity.noContent().build();
    }
}