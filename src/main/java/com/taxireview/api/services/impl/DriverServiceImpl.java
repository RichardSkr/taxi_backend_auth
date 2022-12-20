package com.taxireview.api.services.impl;

import com.taxireview.api.dto.DriverDto;
import com.taxireview.api.dto.DriverResponse;
import com.taxireview.api.exceptions.DriverNotFoundException;
import com.taxireview.api.models.Driver;
import com.taxireview.api.repositories.DriverRepository;
import com.taxireview.api.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverDto createDriver(DriverDto driverDto) {
        Driver driver = new Driver();
        driver.setCarColor(driverDto.getCarColor());
        driver.setCarBrand(driverDto.getCarBrand());
        driver.setLicensePlate(driverDto.getLicensePlate());
        driver.setCarModel(driverDto.getCarModel());
        driver.setName(driverDto.getName());

        Driver newDriver = driverRepository.save(driver);

        DriverDto driverResponse = new DriverDto();
        driverResponse.setId(newDriver.getId());
        driverResponse.setCarColor(newDriver.getCarColor());
        driverResponse.setCarBrand(newDriver.getCarBrand());
        driverResponse.setLicensePlate(newDriver.getLicensePlate());
        driverResponse.setCarModel(newDriver.getCarModel());
        driverResponse.setName(newDriver.getName());
        return driverResponse;
    }

    @Override
    public DriverResponse getAllDrivers(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Driver> drivers = driverRepository.findAll(pageable);
        List<Driver> listOfDrivers = drivers.getContent();

        //map because of returning to a new list

        List<DriverDto> content = listOfDrivers.stream()
                .map(p -> mapToDto(p))
                .collect(Collectors.toList());

        DriverResponse driverResponse = new DriverResponse();
        driverResponse.setContent(content);
        driverResponse.setPageNo(drivers.getNumber());
        driverResponse.setPageSize(drivers.getSize());
        driverResponse.setTotalElements(drivers.getTotalElements());
        driverResponse.setTotalPages(drivers.getTotalPages());
        driverResponse.setLast(drivers.isLast());

        return driverResponse;
    }

    @Override
    public DriverDto getriverById(long id) {
       Driver driver = driverRepository.findById(id).orElseThrow(() -> new DriverNotFoundException("This driver could not been found."));
       return mapToDto(driver);
    }

    @Override
    public DriverDto updateDriver(DriverDto driverDto, long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new DriverNotFoundException("This driver could not been updated."));

        driver.setCarColor(driverDto.getCarColor());
        driver.setCarBrand(driverDto.getCarBrand());
        driver.setLicensePlate(driverDto.getLicensePlate());
        driver.setCarModel(driverDto.getCarModel());
        driver.setName(driverDto.getName());

        Driver updatedDriver = driverRepository.save(driver);
        return mapToDto(updatedDriver);
    }

    @Override
    public void deleteDriverById(long id) {
       Driver driver = driverRepository.findById(id).orElseThrow(() -> new DriverNotFoundException("This driver could not been deleted."));
       driverRepository.delete(driver);

    }


    /**
     * Maping DTO & Entity
     */

    private DriverDto mapToDto(Driver driver) {
        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setCarColor(driver.getCarColor());
        driverDto.setCarBrand(driver.getCarBrand());
        driverDto.setLicensePlate(driver.getLicensePlate());
        driverDto.setCarModel(driver.getCarModel());
        driverDto.setName(driver.getName());
        return driverDto;
    }

    private Driver mapToEntity(DriverDto driverDto) {
        Driver driver = new Driver();
        driver.setId(driverDto.getId());
        driver.setCarColor(driverDto.getCarColor());
        driver.setCarBrand(driverDto.getCarBrand());
        driver.setLicensePlate(driverDto.getLicensePlate());
        driver.setCarModel(driverDto.getCarModel());
        driver.setName(driverDto.getName());
        return driver;
    }
}
