package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Driver;
import com.assignment2.group15.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @GetMapping
    public List<Driver> getAllDrivers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required=false) Integer limit,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone
    ) {
        return driverService.getAllDriver(page, limit, name, address, phone);
    }

    @GetMapping(path="{driverId}")
    public Driver getSingleDriver(
            @PathVariable("driverId") Long driverId
    ) {
        return driverService.getSingleDriver(driverId);
    }

    @PostMapping
    public Driver saveDriver(
            @RequestBody Driver driver,
            @RequestParam Long carId
    ) {
        return driverService.saveDriver(driver, carId);
    }

    @PutMapping(path="{driverId}")
    public Driver updateDriver(
            @PathVariable("driverId") Long driverId,
            @RequestParam(required = false) Long carId,
            @RequestBody Driver driver
    ) {
        return driverService.updateDriver(driver, driverId, carId);
    }

    @DeleteMapping(path="{driverId}")
    public String deleteDriver(
            @PathVariable("driverId") Long driverId
    ) {
        return driverService.deleteDriver(driverId);
    }
}
