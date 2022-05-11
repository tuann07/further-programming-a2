package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Customer;
import com.assignment2.group15.errors.CustomerNotExist;
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
    public List<Customer> getAllCustomers(@RequestParam(required=false) Integer page)
    {
        return customerService.getAllCustomer(page);
    }

    @GetMapping(path="{customerId}")
    public Customer getSingleCustomer(@PathVariable("customerId") long customerId)
    {
        return customerService.getSingleCustomer(customerId);
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer)
    {
        return customerService.saveCustomer(customer);
    }

    @PutMapping(path="{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") long customerId, @RequestBody Customer customer)
    {
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping(path="{customerId}")
    public String deleteCustomer(@PathVariable("customerId") long customerId)
    {
        return customerService.deleteCustomer(customerId);
    }
}
