package com.assignment2.group15.service;

import com.assignment2.group15.entity.Customer;
import com.assignment2.group15.errors.CustomerNotExist;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.hibernate.query.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CustomerService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<Customer> getAllCustomer(int page){
        String hql = "from Customer";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        int limit = 10;
        int firstResult=(page-1)*limit;

        query.setFirstResult(firstResult);
        query.setMaxResults(limit);
        return query.list();
    }

    public Customer getSingleCustomer(long customerId){
        String hql = "from Customer i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", customerId);

        try
        {
            return (Customer) query.getSingleResult();
        }
        catch (Exception e)
        {
            throw new CustomerNotExist();
        }
    }

    public Customer saveCustomer(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
        return customer;
    }
    public Customer updateCustomer(long customerID, Customer customer) {
        String hql = "from Customer i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", customerID);

        try
        {
            query.getSingleResult();
        }
        catch (Exception e)
        {
            throw new CustomerNotExist();
        }

        customer.setId(customerID);
        sessionFactory.getCurrentSession().update(customer);
        return customer;
    }

    public void deleteCustomer(long customerID)
    {
        String hql;
        Query query;

        hql = "delete from Customer i where i.id=:id";
        query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", customerID);
        try
        {
            query.executeUpdate();
        }
        catch (Exception e)
        {
            throw new CustomerNotExist();
        }
        return;
    }
}
