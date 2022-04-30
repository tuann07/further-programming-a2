package com.group15.assignment2;

import model.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.assignment2.group15.service.CustomerService;

@SpringBootApplication
public class Group15Application {

	public static void main(String[] args) {
		ApplicationContext context = new
				AnnotationConfigApplicationContext(AppConfig.class);
		CustomerService service = context.getBean(CustomerService.class);

		Customer customer1 = context.getBean(Customer.class);
		customer1.setName("Hung");
		service.saveCustomer(customer1);
	}

}
