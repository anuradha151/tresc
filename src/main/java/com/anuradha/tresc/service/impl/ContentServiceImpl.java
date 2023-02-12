package com.anuradha.tresc.service.impl;

import com.anuradha.tresc.dto.ContentRequestDto;
import com.anuradha.tresc.model.Content;
import com.anuradha.tresc.repository.ContentRepository;
import com.anuradha.tresc.service.ContentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final ModelMapper modelMapper;


    @Override
    public void save(ContentRequestDto dto) {
        contentRepository.save(modelMapper.map(dto, Content.class));
    }
}
