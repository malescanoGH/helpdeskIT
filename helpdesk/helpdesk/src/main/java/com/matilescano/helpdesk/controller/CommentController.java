package com.matilescano.helpdesk.controller;

import com.matilescano.helpdesk.model.Comment;
import com.matilescano.helpdesk.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/ticket/{idTicket}")
    public List<Comment> getByTicket(@PathVariable Integer idTicket) {
        return commentService.getByTicket(idTicket);
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}