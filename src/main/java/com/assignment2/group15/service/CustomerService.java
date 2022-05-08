package com.assignment2.group15.service;

import com.assignment2.group15.entity.Customer;
import com.assignment2.group15.errors.CustomerNotExist;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.hibernate.query.Query;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Transactional
@Service
public class CustomerService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<Customer> getAllCustomer(Integer page)
    {
        int pageNumber;
        String hql = "from Customer";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        if (page==null || page<1)
        {
            pageNumber=1;
        }
        else
        {
            pageNumber=page;
        }
        int limit = 10;
        int firstResultAt = (pageNumber-1)*limit;
        query.setFirstResult(firstResultAt);
        query.setMaxResults(limit);
        return query.list();
    }

    public Customer getSingleCustomer(long customerId)
    {
        Customer customer = sessionFactory.getCurrentSession().get(Customer.class, customerId);

        if (customer == null)
        {
            throw new CustomerNotExist();
        }

        return customer;
    }

    public Customer saveCustomer(Customer customer)
    {
        customer.setDateCreated(ZonedDateTime.now());
        sessionFactory.getCurrentSession().save(customer);
        return customer;
    }
    public Customer updateCustomer(long customerID, Customer customer) {
        Customer oldCustomer = this.getSingleCustomer(customerID);

        customer.setId(customerID);
        customer.setDateCreated(oldCustomer.getDateCreated());

        sessionFactory.getCurrentSession().merge(customer);
        return customer;
    }

    public String deleteCustomer(long customerID)
    {
        Customer customer = this.getSingleCustomer(customerID);
        sessionFactory.getCurrentSession().delete(customer);
        return "Delete Success";
    }
}
