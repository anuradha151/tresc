package com.anuradha.tresc.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class ResponseDto {

    private boolean success;
    private String message;
    private List<Object> data;
    private String error;


}
