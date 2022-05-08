package com.assignment2.group15.service;

import com.assignment2.group15.entity.Booking;
import com.assignment2.group15.entity.Driver;
import com.assignment2.group15.entity.Invoice;
import com.assignment2.group15.errors.InvoiceNotExist;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Transactional
@Service
public class InvoiceService {

    private SessionFactory sessionFactory;
    private BookingService bookingSerivce;

    @Autowired
    public void setBookingSerivce(BookingService bookingSerivce) {
        this.bookingSerivce = bookingSerivce;
    }

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
            hql += " where i.booking.pickup between :start and :end";
        }

        query = sessionFactory.getCurrentSession().createQuery(hql);

        // filtering
        // by date, only activate if start or end date is presented
        if (start != null || end != null) {
            // try to parse both dates, if there is one,
            // set the start or end date faraway
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

    public Invoice saveInvoice(Long bookingId, Invoice invoice) {
        Booking booking = bookingSerivce.getSingleBooking(bookingId);
        invoice.setBooking(booking);
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

    public Double getRevenueByCustomer(Long customerId, String start, String end) {
        // convert local date
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        // create query to get the sum of total charges of a customer with the pickup date between a start and end
        String hql = "select sum(i.totalCharge) from Invoice i where i.booking.customer.id = :customerId and i.booking.pickup between :start and :end";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("customerId", customerId);
        query.setParameter("start", startDate);
        query.setParameter("end", endDate);

        // get sum
        return (Double) query.getSingleResult();
    }

    public Double getRevenueByDriver(Long driverId, String start, String end) {
        // convert to local date
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        // create query to get the sum of total charges of a driver with the pickup date between a start and end
        String hql = "select sum(i.totalCharge) from Invoice i where i.booking.driver.id = :driverId and i.booking.pickup between :start and :end";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("driverId", driverId);
        query.setParameter("start", startDate);
        query.setParameter("end", endDate);

        // get sum
        return (Double) query.getSingleResult();
    }
}
