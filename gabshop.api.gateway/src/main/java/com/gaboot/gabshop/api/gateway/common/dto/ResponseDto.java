package com.gaboot.gabshop.api.gateway.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto<T> {
    private String message;
    private T datum;
    private List<T> data;
    private boolean success;
    private int lastPage;
    private int totalData;
}
