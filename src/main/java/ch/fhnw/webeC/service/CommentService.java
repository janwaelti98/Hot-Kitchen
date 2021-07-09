package ch.fhnw.webeC.service;

import ch.fhnw.webeC.model.Comment;
import ch.fhnw.webeC.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    public final CommentRepository repo;

    public CommentService(CommentRepository repo) {
        this.repo = repo;
    }

    public List<Comment> getComments() {
        return repo.findAll();
    }

    public Comment add(Comment comment) {
        return repo.save(comment);
    }
}
