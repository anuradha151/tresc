package com.anuradha.tresc.service.impl;

import com.anuradha.tresc.model.Comment;
import com.anuradha.tresc.repository.CommentRepository;
import com.anuradha.tresc.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
