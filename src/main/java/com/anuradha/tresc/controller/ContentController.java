package com.anuradha.tresc.controller;

import com.anuradha.tresc.dto.ContentRequestDto;
import com.anuradha.tresc.dto.ResponseDto;
import com.anuradha.tresc.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PostMapping("/v1")
    public ResponseEntity<ResponseDto> save(@RequestBody ContentRequestDto dto){
        contentService.save(dto);
        return ResponseEntity.ok(ResponseDto.builder().success(true).message("Content added successfully").build());
    }


}
