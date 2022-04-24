package com.assignment2.group15.service;


import com.assignment2.group15.model.Invoice;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class InvoiceService {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Invoice> getAllInvoices() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Invoice");
        return query.list();
    }

    public Invoice saveInvoice(Invoice invoice) {
        sessionFactory.getCurrentSession().save(invoice);
        return invoice;
    }

    public Invoice updateInvoice(long invoiceId, Invoice invoice) {
        invoice.setId(invoiceId);
        sessionFactory.getCurrentSession().update(invoice);
        return invoice;
    }

    public long deleteInvoice(long invoiceId) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from Invoice i where i.id=:id").setParameter("id", invoiceId);
        query.executeUpdate();
        return invoiceId;
    }
}
