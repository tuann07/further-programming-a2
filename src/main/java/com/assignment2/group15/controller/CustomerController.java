package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Customer;
import com.assignment2.group15.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController
{
    private CustomerService customerService;
    @Autowired
    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers(
            @RequestParam(required=false) Integer page,
            @RequestParam(required=false) Integer limit,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone
    ) {
        return customerService.getAllCustomer(page, limit, name, address, phone);
    }

    @GetMapping(path="{customerId}")
    public Customer getSingleCustomer(
            @PathVariable("customerId") Long customerId
    ) {
        return customerService.getSingleCustomer(customerId);
    }

    @PostMapping
    public Customer saveCustomer(
            @RequestBody Customer customer
    ) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping(path="{customerId}")
    public Customer updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestBody Customer customer
    ) {
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping(path="{customerId}")
    public String deleteCustomer(
            @PathVariable("customerId") Long customerId
    ) {
        return customerService.deleteCustomer(customerId);
    }
}
