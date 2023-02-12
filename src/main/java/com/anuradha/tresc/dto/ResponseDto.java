package com.anuradha.tresc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ResponseDto {

    private boolean success;
    private String message;
    private List<Object> data;
    private String error;


}
