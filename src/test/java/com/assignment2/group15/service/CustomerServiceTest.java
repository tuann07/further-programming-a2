package com.assignment2.group15.service;

import com.assignment2.group15.entity.*;
import com.assignment2.group15.errors.CustomerNotExist;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest
{
    @Autowired
    private CustomerService customerService;

    // number of rows to be generated in the database
    final int NUM_OF_CUSTOMERS = 5;

    @BeforeAll
    void setUp()
    {
        for (int i = 1; i < NUM_OF_CUSTOMERS + 1; i++) {
            customerService.saveCustomer(new Customer());
        }
    }
    @Test
    @Order(1)
    void getAllCustomerTest()
    {
        List<Customer> result = customerService.getAllCustomer(null, null);

        assertEquals(5, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(5, result.get(result.size()-1).getId());
    }

    @Test
    @Order(1)
    void getSingleCustomerTest()
    {
        Customer customerDb = customerService.getSingleCustomer(1L);

        assertEquals(1, customerDb.getId());
    }

    @Test
    @Order(1)
    void getSingleCustomerNotExistTest()
    {
        assertThrows(CustomerNotExist.class, () -> customerService.getSingleCustomer(55L));
    }

    @Test
    @Order(5)
    void updateCustomerTest()
    {
        Customer customer = new Customer();
        customer.setAddress("133 Cong Hoa");
        customerService.updateCustomer(1L, customer);
        Customer customerDb = customerService.getSingleCustomer(1L);

        assertEquals("133 Cong Hoa", customerDb.getAddress());
    }

    @Test
    @Order(5)
    void updateCustomerNotExistTest()
    {
        assertThrows(CustomerNotExist.class, () -> customerService.updateCustomer(55L, new Customer()));
    }
    @Test
    @Order(10)
    void saveCustomerTest()
    {
        customerService.saveCustomer(new Customer());
        Customer customerDb = customerService.getSingleCustomer(5L);
        assertEquals(5, customerDb.getId());
    }
    @Test
    @Order(10)
    void deleteCustomerTest()
    {
        customerService.deleteCustomer(1L);
        assertThrows(CustomerNotExist.class, () -> customerService.getSingleCustomer(1L));
    }
}