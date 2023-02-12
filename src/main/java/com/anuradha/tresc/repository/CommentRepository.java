package com.anuradha.tresc.repository;

import com.anuradha.tresc.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
