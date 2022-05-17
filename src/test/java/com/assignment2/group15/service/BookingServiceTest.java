package com.assignment2.group15.service;

import com.assignment2.group15.exception.BookingNotExist;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.assignment2.group15.entity.*;

import java.util.List;

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
    final int NUM_OF_BOOKINGS = 5;

    @BeforeAll
    void setUp() {
        for (int i = 1; i < NUM_OF_OTHERS + 1; i++) {
            carService.saveCar(new Car());
            customerService.saveCustomer(new Customer());
        }

        for (int i = 1; i < NUM_OF_OTHERS + 1; i++) {
            driverService.saveDriver(new Driver(), (long) i);
        }

        for (int i = 1; i < NUM_OF_BOOKINGS + 1; i++) {
            bookingService.saveBooking(new Booking(), (long) i, (long) i);
        }
    }
    @Test
    @Order(1)
    void getAllBookingTest()
    {
        List<Booking> result = bookingService.getAllBooking(null, null, null, null);

        assertEquals(5, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(5, result.get(result.size()-1).getId());
    }

    @Test
    @Order(1)
    void getSingleBookingTest()
    {
        Booking bookingDb = bookingService.getSingleBooking(1L);

        assertEquals(1, bookingDb.getId());
    }

    @Test
    @Order(1)
    void getSingleBookingNotExistTest()
    {
        assertThrows(BookingNotExist.class, () -> bookingService.getSingleBooking(99L));
    }

    @Test
    @Order(5)
    void updateBookingTest()
    {
        Booking booking = new Booking();
        booking.setDistance((long) 155.0);
        bookingService.updateBooking(1L, booking);
        Booking bookingDb = bookingService.getSingleBooking(1L);

        assertEquals((long)155.0, bookingDb.getDistance());
    }

    @Test
    @Order(5)
    void updateBookingNotExistTest()
    {
        assertThrows(BookingNotExist.class, () -> bookingService.updateBooking(55L, new Booking()));
    }

    @Test
    @Order(10)
    void saveBookingTest()
    {
        bookingService.saveBooking(new Booking(), 1L, 1L);
        Booking bookingDb = bookingService.getSingleBooking(6L);
        assertEquals(6, bookingDb.getId());
    }
    @Test
    @Order(10)
    void deleteBookingTest()
    {
        bookingService.deleteBooking(1L);
        assertThrows(BookingNotExist.class, () -> bookingService.getSingleBooking(1L));
    }
}