package com.assignment2.group15.service;

import com.assignment2.group15.entity.*;
import com.assignment2.group15.exception.InvoiceNotExist;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InvoiceServiceTest {

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
            Booking booking = new Booking();
            booking.setPickup(LocalDate.of(2022, 10, i).atStartOfDay());
            bookingService.saveBooking(booking, (long) i, (long) i);
        }

        for (int i = 1; i < NUM_OF_INVOICES + 1; i++) {
            Invoice invoice = new Invoice();
            invoice.setTotalCharge((double) i);
            invoiceService.saveInvoice((long) i, invoice);
        }
    }

    @Test
    @Order(1)
    void getAllInvoicesTest() {
        // should get all items from 1 to 5
        List<Invoice> result = invoiceService.getAllInvoices(null, null, null, null);

        assertEquals(5, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(5, result.get(result.size() - 1).getId());
    }

    @Test
    @Order(1)
    void getAllInvoicesPaginationTest() {
        // With 5 invoices, second page with 2 in each should return full 2 results -> 3rd, 4th items
        List<Invoice> fullPage = invoiceService.getAllInvoices(2, 2, null, null);
        assertEquals(2, fullPage.size());
        assertEquals(3, fullPage.get(0).getId());
        assertEquals(4, fullPage.get(fullPage.size() - 1).getId());

        // With 5 invoices, second page with 3 in each should return 2 results -> 4th, 5th items
        List<Invoice> partialPage = invoiceService.getAllInvoices(2, 3, null, null);
        assertEquals(2, partialPage.size());
        assertEquals(4, partialPage.get(0).getId());
        assertEquals(5, partialPage.get(partialPage.size() - 1).getId());
    }

    @Test
    @Order(1)
    void getAllInvoicesDateFilteringTest() {
        List<Invoice> result = invoiceService.getAllInvoices(null, null, "2022-10-02", "2022-10-04");
        assertEquals(3, result.size());

        List<Invoice> resultWithPagination = invoiceService.getAllInvoices(2, 2, "2022-10-02", "2022-10-04");
        assertEquals(1, resultWithPagination.size());
    }

    @Test
    @Order(1)
    void getSingleInvoiceTest() {
        Invoice invoiceDb = invoiceService.getSingleInvoice(1L);

        assertEquals(1, invoiceDb.getId());
    }

    @Test
    @Order(1)
    void getSingleInvoiceNotExistTest() {
        assertThrows(InvoiceNotExist.class, () -> invoiceService.getSingleInvoice(99L));
    }

    @Test
    @Order(1)
    void getRevenueByCustomerTest() {
        Double result = invoiceService.getRevenueByCustomer(2L, "2022-10-02", "2022-10-04");
        assertEquals(2, result);
    }

    @Test
    @Order(1)
    void getRevenueByDriverTest() {
        Double result = invoiceService.getRevenueByDriver(2L, "2022-10-02", "2022-10-04");
        assertEquals(2, result);
    }

    @Test
    @Order(70)
    void updateInvoiceTest() {
        Invoice invoice = new Invoice();
        invoice.setTotalCharge(123.0);
        invoiceService.updateInvoice(1L, invoice);
        Invoice invoiceDb = invoiceService.getSingleInvoice(1L);

        assertEquals(123.0, invoiceDb.getTotalCharge());
    }

    @Test
    @Order(70)
    void updateInvoiceNotExistTest() {
        assertThrows(InvoiceNotExist.class, () -> invoiceService.updateInvoice(99L, new Invoice()));
    }

    @Test
    @Order(80)
    void saveInvoiceTest() {
        invoiceService.saveInvoice(10L, new Invoice());
        Invoice invoiceDb = invoiceService.getSingleInvoice(6L);
        assertEquals(6, invoiceDb.getId());
    }

    @Test
    @Order(90)
    void deleteInvoiceTest() {
        invoiceService.deleteInvoice(1L);

        assertThrows(InvoiceNotExist.class, () -> invoiceService.getSingleInvoice(1L));
    }

    @Test
    @Order(90)
    void deleteInvoiceNotExistTest() {
        assertThrows(InvoiceNotExist.class, () -> invoiceService.deleteInvoice(99L));
    }
}