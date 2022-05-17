package com.assignment2.group15.service;

import com.assignment2.group15.entity.Booking;
import com.assignment2.group15.entity.Invoice;
import com.assignment2.group15.exception.InvoiceNotExist;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Transactional
@Service
public class InvoiceService {

    private SessionFactory sessionFactory;
    private BookingService bookingService;

    private static final int PAGE_DEFAULT = 1;
    private static final int LIMIT_DEFAULT = 10;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Invoice> getAllInvoices(Integer page, Integer limit, String start, String end) {
        String hql;
        LocalDateTime startDate, endDate;
        Query query;
        int pageNumber, limitNumber;

        hql = "from Invoice i ";

        if (start != null && end != null) {
            hql += "where i.booking.pickup between :start and :end";
        }

        query = sessionFactory.getCurrentSession().createQuery(hql);

        // filtering
        // by date, only activate if start or end date is presented
        if (start != null && end != null) {
            startDate = LocalDate.parse(start).atStartOfDay();
            endDate = LocalDate.parse(end).plusDays(1).atStartOfDay().minusSeconds(1);
            System.out.println(endDate);

            query.setParameter("start", startDate);
            query.setParameter("end", endDate);
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

    public Invoice getSingleInvoice(Long invoiceId) {
        Invoice invoice = sessionFactory.getCurrentSession().get(Invoice.class, invoiceId);

        if (invoice == null) {
            throw new InvoiceNotExist();
        }

        return invoice;
    }

    public Invoice saveInvoice(Long bookingId, Invoice invoice) {
        // find and set booking
        Booking booking = bookingService.getSingleBooking(bookingId);
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
        invoice.setBooking(oldInvoice.getBooking());
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
        LocalDateTime startDate = LocalDate.parse(start).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(end).plusDays(1).atStartOfDay().minusSeconds(1);

        // create query to get the sum of total charges of a customer with the pickup date between a start and end
        String hql = "select sum(i.totalCharge) from Invoice i " +
                "where i.booking.customer.id = :customerId and " +
                "i.booking.pickup between :start and :end";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("customerId", customerId);
        query.setParameter("start", startDate);
        query.setParameter("end", endDate);

        // get sum
        return (Double) query.getSingleResult();
    }

    public Double getRevenueByDriver(Long driverId, String start, String end) {
        // convert local date
        LocalDateTime startDate = LocalDate.parse(start).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(end).plusDays(1).atStartOfDay().minusSeconds(1);

        // create query to get the sum of total charges of a driver with the pickup date between a start and end
        String hql = "select sum(i.totalCharge) from Invoice i " +
                "where i.booking.driver.id = :driverId and " +
                "i.booking.pickup between :start and :end";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("driverId", driverId);
        query.setParameter("start", startDate);
        query.setParameter("end", endDate);

        // get sum
        return (Double) query.getSingleResult();
    }
}
