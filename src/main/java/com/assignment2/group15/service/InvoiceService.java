package com.assignment2.group15.service;

import com.assignment2.group15.entity.Invoice;
import com.assignment2.group15.errors.InvoiceNotExist;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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

    public List<Invoice> getAllInvoices(Integer page, String start, String end) {
        String hql;
        LocalDate startDate, endDate;
        Query query;
        int pageNumber;

        hql = "from Invoice i";

        if (start != null || end != null) {
            hql += " where i.pickUpTime between :start and :end";
        }

        query = sessionFactory.getCurrentSession().createQuery(hql);

        // filtering
        if (start != null || end != null) {
            startDate = start == null ? LocalDate.of(1970, 1, 1) : LocalDate.parse(start);
            endDate = end == null ? LocalDate.of(2050, 1, 1) : LocalDate.parse(end);
            query.setParameter("start", startDate);
            query.setParameter("end", endDate);
        }

        // paging
        // check if the user provide a page number
        // if not, return page 1 by default
        if (page == null || page < 1) {
            pageNumber = 1;
        } else {
            pageNumber = page;
        }

        // limit number of results per page
        int limit = 10;

        // index of the first result
        // page 1 starts at index 0
        // page n starts at index (n - 1) * limit
        int firstResultAt = (pageNumber - 1) * limit;

        query.setFirstResult(firstResultAt);  // set location of the first result
        query.setMaxResults(limit);  // set number of results

        return query.list();
    }

    public Invoice getSingleInvoice(Long invoiceId) {
        Invoice invoice = sessionFactory.getCurrentSession().get(Invoice.class, invoiceId);

        if (invoice == null) {
            throw new InvoiceNotExist();
        }

        return invoice;
    }

    public Invoice saveInvoice(Invoice invoice) {
        // override the date created with the current time
        invoice.setDateCreated(ZonedDateTime.now());
        sessionFactory.getCurrentSession().save(invoice);
        return invoice;
    }

    public Invoice updateInvoice(Long invoiceId, Invoice invoice) {
        // ensure the item in the database
        Invoice oldInvoice = this.getSingleInvoice(invoiceId);

        // get the old date and attach the id
        invoice.setId(invoiceId);
        invoice.setDateCreated(oldInvoice.getDateCreated());

        sessionFactory.getCurrentSession().merge(invoice);
        return invoice;
    }

    public String deleteInvoice(Long invoiceId) {
        Invoice invoice = this.getSingleInvoice(invoiceId);

        sessionFactory.getCurrentSession().delete(invoice);

        return "Invoice deleted";
    }
}
