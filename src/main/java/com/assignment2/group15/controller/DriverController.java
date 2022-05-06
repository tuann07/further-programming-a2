package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Driver;
import com.assignment2.group15.errors.DriverNotExist;
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
    public List<Driver> getAllDrivers(@RequestParam(required = false) String page) {
        int pageNumber;

        try {
            if (page == null) {
                pageNumber = 1;
            } else {
                pageNumber = Integer.parseInt(page);
            }
        } catch (Exception e) {
            throw new DriverNotExist();
        }
        return driverService.getAllDriver(pageNumber);
    }

    @GetMapping(path="{driverId}")
    public Driver getSingleDriver(@PathVariable("driverId") long driverId) {
        return driverService.getSingleDriver(driverId);
    }

    @PostMapping
    public Driver saveDriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }

    @PutMapping(path="{driverId}")
    public Driver updateDriver(@PathVariable("driverId") long driverId, @RequestBody Driver driver) {
        return driverService.updateDriver(driverId, driver);
    }

    @DeleteMapping(path="{driverId}")
    public void deleteDriver(@PathVariable("driverId") long driverId) {
        driverService.deleteDriver(driverId);
        return;
    }
}
