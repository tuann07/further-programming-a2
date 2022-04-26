package com.assignment2.group15.service;


import com.assignment2.group15.error.InvoiceNotExist;
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

    public List<Invoice> getAllInvoices(int page) {
        String hql = "from Invoice";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        int limit = 10;
        int firstResultAt = (page - 1) * limit;

        query.setFirstResult(firstResultAt);
        query.setMaxResults(limit);

        return query.list();
    }

    public Invoice getSingleInvoice(long invoiceId) {
        String hql = "from Invoice i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", invoiceId);
        try {
            return (Invoice) query.getSingleResult();
        } catch (Exception e) {
            throw new InvoiceNotExist();
        }
    }

    public Invoice saveInvoice(Invoice invoice) {
        sessionFactory.getCurrentSession().save(invoice);
        return invoice;
    }

    public Invoice updateInvoice(long invoiceId, Invoice invoice) {
        String hql;
        Query query;

        hql = "from Invoice i where i.id=:id";
        query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", invoiceId);
        try {
            query.getSingleResult();
        } catch (Exception e) {
            throw new InvoiceNotExist();
        }

        invoice.setId(invoiceId);
        sessionFactory.getCurrentSession().update(invoice);
        return invoice;
    }

    public void deleteInvoice(long invoiceId) {
        String hql;
        Query query;

        hql = "from Invoice i where i.id=:id";
        query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", invoiceId);
        try {
            query.getSingleResult();
        } catch (Exception e) {
            throw new InvoiceNotExist();
        }

        hql = "delete from Invoice i where i.id=:id";
        query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", invoiceId);
        query.executeUpdate();

        return;
    }
}
