package com.taxireview.api.services;

import com.taxireview.api.dto.DriverDto;
import com.taxireview.api.dto.DriverResponse;

public interface DriverService {
    DriverDto createDriver(DriverDto driverDto);
    DriverResponse getAllDrivers(int pageNo, int pageSize);
    DriverDto getriverById(long id);
    DriverDto updateDriver(DriverDto driverDto, long id);
    void deleteDriverById(long id);
}
