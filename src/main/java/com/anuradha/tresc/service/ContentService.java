package com.anuradha.tresc.service;

import com.anuradha.tresc.dto.CommentRequestDto;
import com.anuradha.tresc.dto.ContentRequestDto;

public interface ContentService {

    void save(ContentRequestDto dto);

    void comment(CommentRequestDto dto);

}
