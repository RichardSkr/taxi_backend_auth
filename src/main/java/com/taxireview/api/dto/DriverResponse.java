package com.taxireview.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class DriverResponse {
    private List<DriverDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
