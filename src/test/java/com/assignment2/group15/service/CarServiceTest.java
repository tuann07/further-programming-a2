package com.assignment2.group15.service;

import com.assignment2.group15.exception.NotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.assignment2.group15.entity.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarServiceTest {
    @Autowired
    private CarService carService;

    // number of rows to be generated in the database

    final int NUM_OF_CARS = 5;

    @BeforeAll
    void setUp() {
        for (int i = 1; i < NUM_OF_CARS + 1; i++) {
            carService.saveCar(new Car());
        }
    }

    @Test
    @Order(1)
    void getAllCarTest() {
        List<Car> result = carService.getAllCar(null, null, null, null, null);

        assertEquals(5, result.size());
        assertEquals(1, result.get(0).getcarID());
        assertEquals(5, result.get(result.size() - 1).getcarID());
    }

    @Test
    @Order(1)
    void getSingleCarTest() {
        Car carDb = carService.getSingleCar(1L);

        assertEquals(1, carDb.getcarID());
    }

    @Test
    @Order(1)
    void getSingleCarNotExistTest()
    {
        assertThrows(NotFoundException.class, () -> carService.getSingleCar(55L));
    }

    @Test
    @Order(10)
    void saveCarTest()
    {
        carService.saveCar(new Car());
        Car carDb = carService.getSingleCar(6L);
        assertEquals(6, carDb.getcarID());
    }

    @Test
    @Order(5)
    void updateCarTest()
    {
        Car car = new Car();
        car.setColor("red lotus");
        carService.updateCar(1L, car);
        Car carDb = carService.getSingleCar(1L);

        assertEquals("red lotus", carDb.getColor());
    }

    @Test
    @Order(5)
    void updateCarNotExistTest()
    {
        assertThrows(NotFoundException.class, () -> carService.updateCar(55L, new Car()));
    }

    @Test
    @Order(10)
    void deleteCarTest()
    {
        carService.deleteCar(1L);
        assertThrows(NotFoundException.class, () -> carService.getSingleCar(1L));
    }
}