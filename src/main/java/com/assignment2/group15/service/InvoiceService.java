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
        String hql = "from Invoice";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public Invoice getSingleInvoice(long invoiceId) {
        String hql = "from Invoice i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", invoiceId);
        return (Invoice) query.getSingleResult();
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

    public void deleteInvoice(long invoiceId) {
        String hql = "delete from Invoice i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", invoiceId);
        query.executeUpdate();
        return;
    }
}
