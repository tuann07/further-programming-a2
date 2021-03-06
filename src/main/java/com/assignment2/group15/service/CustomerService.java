package com.assignment2.group15.service;

import com.assignment2.group15.entity.Customer;
import com.assignment2.group15.exception.NotFoundException;
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

    private SessionFactory sessionFactory;

    private static final int PAGE_DEFAULT = 1;
    private static final int LIMIT_DEFAULT = 10;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Customer> getAllCustomer(Integer page, Integer limit, String name, String address, String phone)
    {
        String hql;
        Query query;
        int pageNumber, limitNumber;
        boolean isSearch = false;

        hql = "from Customer c ";

        if (name != null || address != null || phone != null) {
            hql += "where ";

            if (name != null) {
                hql += "c.name = :name ";
                isSearch = true;
            }
            if (address != null) {
                hql += (isSearch ? "and " : "") + "c.address = :address ";
                isSearch = true;
            }
            if (phone != null) {
                hql += (isSearch ? "and " : "") + "c.phone = :phone";
            }
        }

        query = sessionFactory.getCurrentSession().createQuery(hql);

        if (name != null) {
            query.setParameter("name", name);
        }
        if (address != null) {
            query.setParameter("address", address);
        }
        if (phone != null) {
            query.setParameter("phone", phone);
        }

        // paging
        // if not provide or negative, set default
        if (page == null || page < 1) {
            pageNumber = PAGE_DEFAULT;
        } else {
            pageNumber = page;
        }

        // if not provide or negative, set default
        if (limit == null || limit < 1) {
            limitNumber = LIMIT_DEFAULT;
        } else {
            limitNumber = limit;
        }

        // index of the first result
        // page 1 starts at index 0
        // page n starts at index (n - 1) * limit
        int firstResultAt = (pageNumber - 1) * limitNumber;

        query.setFirstResult(firstResultAt);  // set location of the first result
        query.setMaxResults(limitNumber);  // set number of results

        return query.list();
    }

    public Customer getSingleCustomer(Long customerId)
    {
        Customer customer = sessionFactory.getCurrentSession().get(Customer.class, customerId);

        if (customer == null) {
            throw new NotFoundException("Car with id " + customerId + " not found");
        }

        return customer;
    }

    public Customer saveCustomer(Customer customer)
    {
        customer.setDateCreated(ZonedDateTime.now());
        sessionFactory.getCurrentSession().save(customer);
        return customer;
    }

    public Customer updateCustomer(Long customerID, Customer customer) {
        // keep old properties
        Customer oldCustomer = this.getSingleCustomer(customerID);

        if (customer.getName() == null) customer.setName(oldCustomer.getName());
        if (customer.getPhone() == null) customer.setPhone(oldCustomer.getPhone());
        if (customer.getAddress() == null) customer.setAddress(oldCustomer.getAddress());

        customer.setDateCreated(oldCustomer.getDateCreated());
        customer.setId(customerID);

        sessionFactory.getCurrentSession().merge(customer);
        return customer;
    }

    public String deleteCustomer(Long customerId)
    {
        Customer customer = this.getSingleCustomer(customerId);
        sessionFactory.getCurrentSession().delete(customer);
        return "Delete Success";
    }
}
