package com.assignment2.group15.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.assignment2.group15.entity.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingServiceTest
{
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private CarService carService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private CustomerService customerService;

    // number of rows to be generated in the database
    final int NUM_OF_OTHERS = 10;
    final int NUM_OF_INVOICES = 5;

    @BeforeAll
    void setUp() {
        for (int i = 1; i < NUM_OF_OTHERS + 1; i++) {
            carService.saveCar(new Car());
            customerService.saveCustomer(new Customer());
        }

        for (int i = 1; i < NUM_OF_OTHERS + 1; i++) {
            driverService.saveDriver(new Driver(), (long) i);
        }

        for (int i = 1; i < NUM_OF_OTHERS + 1; i++) {
            bookingService.saveBooking(new Booking(), (long) i, (long) i);
        }

        for (int i = 1; i < NUM_OF_INVOICES + 1; i++) {
            invoiceService.saveInvoice((long) i, new Invoice());
        }
    }
    @Test
    void getAllBooking() {
    }

    @Test
    void getSingleBooking() {
    }

    @Test
    void saveBooking() {
    }

    @Test
    void updateBooking() {
    }

    @Test
    void deleteBooking() {
    }
}