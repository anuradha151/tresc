package com.anuradha.tresc.service.impl;

import com.anuradha.tresc.dto.CommentRequestDto;
import com.anuradha.tresc.dto.ContentRequestDto;
import com.anuradha.tresc.model.Comment;
import com.anuradha.tresc.model.Content;
import com.anuradha.tresc.repository.CommentRepository;
import com.anuradha.tresc.repository.ContentRepository;
import com.anuradha.tresc.service.ContentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    @Override
    public void save(ContentRequestDto dto) {
        contentRepository.save(modelMapper.map(dto, Content.class));
    }

    @Override
    public void comment(CommentRequestDto dto) {
        if (dto.getComment() == null || dto.getComment().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Content not found");
        Content content = contentRepository.findById(dto.getContentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Content not found"));
        commentRepository.save(Comment.builder().comment(dto.getComment()).content(content).build());
    }

}
