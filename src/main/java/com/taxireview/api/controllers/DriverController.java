package com.taxireview.api.controllers;

import com.taxireview.api.dto.DriverDto;
import com.taxireview.api.dto.DriverResponse;
import com.taxireview.api.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class DriverController {
    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("driver/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DriverDto> createDriver(@RequestBody DriverDto driverDto) {
        return new ResponseEntity<>(driverService.createDriver(driverDto), HttpStatus.CREATED);
    }

    @GetMapping("driver")
    public ResponseEntity<DriverResponse> getDrivers(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(driverService.getAllDrivers(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("driver/{id}")
    public ResponseEntity<DriverDto> driverDetail(@PathVariable int id) {
        return ResponseEntity.ok(driverService.getriverById(id));
    }

    @PutMapping("driver/{id}/update")
    public ResponseEntity<DriverDto> updateDriver(@RequestBody DriverDto driverDto, @PathVariable ("id") int driverId) {
        DriverDto response = driverService.updateDriver(driverDto, driverId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("driver/{id}/delete")
    public ResponseEntity<String> deleteDriver(@PathVariable("id") int driverId) {
        driverService.deleteDriverById(driverId);
        return new ResponseEntity<>("Driver deleted.", HttpStatus.OK);
    }

}
