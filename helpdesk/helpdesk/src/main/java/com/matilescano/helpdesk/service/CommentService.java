package com.matilescano.helpdesk.service;

import com.matilescano.helpdesk.model.Comment;
import com.matilescano.helpdesk.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getByTicket(Integer idTicket) {
        return commentRepository.findByTicketIdTicket(idTicket);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }
}