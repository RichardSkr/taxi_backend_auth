package com.taxireview.api.dto;

import lombok.Data;

@Data
public class DriverDto {
    private long id;
    private String name;

    private String carBrand;
    private String carModel;
    private String carColor;
    private String licensePlate;
}
