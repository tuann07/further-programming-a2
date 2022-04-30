package com.assignment2.group15.service;

import com.assignment2.group15.entity.Invoice;
import com.assignment2.group15.errors.InvoiceNotExist;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Transactional
@Service
public class InvoiceService {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Invoice> getAllInvoices(Integer page, ZonedDateTime start, ZonedDateTime end) {
        String hql = "from Invoice";
        // filtering
        if (start != null && end != null) {
            hql += " where i.invoiceDate between :start and :end";
        }

        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        if (start != null && end != null) {
            query.setParameter("start", start);
            query.setParameter("end", end);
        }

        // paging
        Integer pageNumber;

        // check if the user provide a page number
        // if not, return page 1 by default
        if (page == null || page < 1) {
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
        Invoice invoice = sessionFactory.getCurrentSession().get(Invoice.class, invoiceId);

        if (invoice == null) {
            throw new InvoiceNotExist(); // throw bad request for not found item
        }

        return invoice;  // try getting an item
    }

    public Invoice saveInvoice(Invoice invoice) {
        invoice.setDateCreated(ZonedDateTime.now());
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

    public String deleteInvoice(Long invoiceId) {
        // ensure the item in the database
        Invoice invoice = this.getSingleInvoice(invoiceId);

        sessionFactory.getCurrentSession().delete(invoice);

        return "Invoice deleted";
    }
}
