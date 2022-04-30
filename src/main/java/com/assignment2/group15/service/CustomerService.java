//package com.assignment2.group15.service;
//
//import com.assignment2.group15.entity.Customer;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.assignment2.group15.errors.CustomerNotExist;
//
//import javax.management.Query;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Transactional
//@Service
//public class CustomerService {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//    private  String customer;
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//
//        public void saveCustomer(Customer customer){
//            sessionFactory.getCurrentSession().save(customer);
//        }
//    }
//
//    public Customer getSingleCustomer(long customerId) {
//        String hql = "from Customer i where i.id=:id";
//        Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", customerId);
//
//        try {
//            return (Customer) query.getSingleResult();
//        } catch (Exception e) {
//            throw new CustomerNotExist();
//        }
//    }
//
//    public Customer getSingleCustomer(long customerId){
//        String hql = "from Customer c where c.id=: id";
//        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", customerId)
//    }
//
//    public Customer saveCustomer(Customer customer) {
//        sessionFactory.getCurrentSession().save(customer);
//        return customer;
//    }
//
//    public Customer updateCustomer(long customerId, Customer customer) {
//        String hql;
//        Query query;
//
//        hql = "from Customer c where c.id=:id";
//        query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", customerId);
//        try {
//            query.getSingleResult();
//        } catch (Exception e) {
//            throw new CustomerNotExist();
//        }
//
//        // attach the id to the object and update it
//        customer.setId(customerId);
//        sessionFactory.getCurrentSession().update(customer);
//        return customer;
//    }
//
//    public void deleteCustomer(long customerId) {
//        String hql;
//        Query query;
//        hql = "delete from Customer i where i.id=:id";
//        query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", customerId);
//        try {
//            query.executeUpdate();  // try deleting the item
//        } catch (Exception e) {
//            throw new CustomerNotExist();  // error means the item not found
//        }
//        return;
//    }
//}
