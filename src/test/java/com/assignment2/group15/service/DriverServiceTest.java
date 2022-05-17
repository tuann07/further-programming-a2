package com.assignment2.group15.service;

import com.assignment2.group15.exception.DriverNotExist;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.assignment2.group15.entity.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DriverServiceTest
{
    @Autowired
    private CarService carService;
    @Autowired
    private DriverService driverService;

    // number of rows to be generated in the database
    final int NUM_OF_OTHERS = 10;
    final int NUM_OF_DRIVERS = 5;

    @BeforeAll
    void setUp()
    {
        for (int i = 1; i < NUM_OF_OTHERS + 1; i++) {
            carService.saveCar(new Car());
        }

        for (int i = 1; i < NUM_OF_DRIVERS + 1; i++) {
            driverService.saveDriver(new Driver(), (long) i);
        }
    }
    @Test
    @Order(1)
    void getAllDriverTest()
    {
        List<Driver> result = driverService.getAllDriver(null, null, null, null, null);

        assertEquals(5, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(5, result.get(result.size()-1).getId());
    }

    @Test
    @Order(1)
    void getSingleDriverTest()
    {
        Driver driverDb = driverService.getSingleDriver(1L);

        assertEquals(1, driverDb.getId());
    }

    @Test
    @Order(1)
    void getSingleDriverNotExistTest()
    {
        assertThrows(DriverNotExist.class, () -> driverService.getSingleDriver(55L));
    }

    @Test
    @Order(5)
    void updateDriverTest()
    {
        Driver driver = new Driver();
        driver.setLicense("55X3-13145");
        driverService.updateDriver(driver, 1L, 1L);
        Driver driverDb = driverService.getSingleDriver(1L);

        assertEquals("55X3-13145", driverDb.getLicense());
    }

    @Test
    @Order(5)
    void updateDriverNotExistTest()
    {
        assertThrows(DriverNotExist.class, () -> driverService.updateDriver(new Driver(), 55L, 55L));
    }
    @Test
    @Order(10)
    void saveDriverTest()
    {
        driverService.saveDriver(new Driver(), 6L);
        Driver driverDb = driverService.getSingleDriver(6L);
        assertEquals(6, driverDb.getId());
    }
    @Test
    @Order(10)
    void deleteDriverTest()
    {
        driverService.deleteDriver(1L);
        assertThrows(DriverNotExist.class, () -> driverService.getSingleDriver(1L));
    }
}