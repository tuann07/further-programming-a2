package com.assignment2.group15.service;


import com.assignment2.group15.error.InvoiceNotExist;
import com.assignment2.group15.model.Invoice;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class InvoiceService {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Invoice> getAllInvoices(Integer page) {
        String hql = "from Invoice";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        Integer pageNumber;

        // check if the user provide a page number
        // if not, return page 1 by default
        if (page == null) {
            pageNumber = 1;
        } else {
            pageNumber = page;
        }

        // paging
        // limit number of results each page
        int limit = 10;

        // index of the first result
        // page 1 starts at index 0
        // page 2 starts at index 1 * limit
        int firstResultAt = (pageNumber - 1) * limit;

        query.setFirstResult(firstResultAt);  // set location of the first result
        query.setMaxResults(limit);  // set number of results

        return query.list();
    }

    public Invoice getSingleInvoice(Long invoiceId) {
        String hql = "from Invoice i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", invoiceId);
        
        try {
            return (Invoice) query.getSingleResult();  // try getting an item
        } catch (Exception e) {
            throw new InvoiceNotExist();  // error means the item not found
        }
    }

    public Invoice saveInvoice(Invoice invoice) {
        sessionFactory.getCurrentSession().save(invoice);
        return invoice;
    }

    public Invoice updateInvoice(Long invoiceId, Invoice invoice) {
        // ensure the item in the database
        this.getSingleInvoice(invoiceId);

        // attach the id to the object and update it
        invoice.setId(invoiceId);
        sessionFactory.getCurrentSession().update(invoice);
        return invoice;
    }

    public void deleteInvoice(Long invoiceId) {
        // ensure the item in the database
        this.getSingleInvoice(invoiceId);

        String hql = "delete from Invoice i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", invoiceId);
        query.executeUpdate();  // try deleting the item
        return;
    }
}
