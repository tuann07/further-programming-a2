package controller;

import com.group15.assignment2.error.CustomerNotExist;
import com.group15.assignment2.model.Customer;
import com.group15.assignment2.service.CustomerService;
import error.CustomerNotExist;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(@RequestParam(required = false) String page) {
        int pageNumber;

        try {
            if (page == null) {
                pageNumber = 1;
            } else {
                pageNumber = Integer.parseInt(page);
            }
        } catch (Exception e) {
            throw new CustomerNotExist();
        }

        return customerService.getAllCustomers(pageNumber);
    } 
    
    @GetMapping(path="{customerId}")
    public Customer getSingleCustomer(@PathVariable("customerId") long customerId) {
        return customerService.getSingleCustomer(customerId);
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping(path="{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") long customerId, @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping(path="{customerId}")
    public void deleteCustomer(@PathVariable("customerId") long customerId) {
        customerService.deleteCustomer(customerId);
        return;
    }
}


}
