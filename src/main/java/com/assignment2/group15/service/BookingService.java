package com.assignment2.group15.service;

import com.assignment2.group15.entity.Customer;
import com.assignment2.group15.entity.Driver;
import com.assignment2.group15.errors.BookingNotExist;
import com.assignment2.group15.entity.Booking;
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
public class BookingService {
    private SessionFactory sessionFactory;
    private CustomerService customerService;
    private DriverService driverService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setDriverService(DriverService driverService)
    {
        this.driverService = driverService;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    public List<Booking> getAllBooking(Integer page, String start, String end)
    {
        String hql;
        LocalDate startDate, endDate;
        Query query;
        int pageNumber;

        hql = "from Booking b";

        if (start!=null || end !=null)
        {
            hql+=" where b.pickup between :start and :end";
        }

        query = sessionFactory.getCurrentSession().createQuery(hql);

        if (start!=null || end !=null)
        {
            startDate = start == null ? LocalDate.of(1970, 1, 1) : LocalDate.parse(start);
            endDate = end == null ? LocalDate.of(2050, 1, 1) : LocalDate.parse(end);

            query.setParameter("start", startDate);
            query.setParameter("end", endDate);
        }

        if (page == null || page < 1)
        {
            pageNumber = 1;
        }
        else
        {
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
    public Booking getSingleBooking(Long bookID)
    {
        Booking booking = sessionFactory.getCurrentSession().get(Booking.class, bookID);
        
        if (booking == null)
        {
        	throw new BookingNotExist();
        }
        
        return booking;
    }
    public Booking saveBooking(Booking booking, Long customerId, Long driverId)
    {
        Customer customer1 = customerService.getSingleCustomer(customerId);
        Driver driver1 = driverService.getSingleDriver(driverId);
        booking.setCustomer(customer1);
        booking.setDriver(driver1);
    	booking.setDateCreated(ZonedDateTime.now());
        sessionFactory.getCurrentSession().save(booking);
        return booking;
    }
    
    public Booking updateBooking(Long bookID, Booking booking)
    {
        Booking oldBooking = this.getSingleBooking(bookID);
        booking.setId(bookID);
        booking.setCustomer(oldBooking.getCustomer());
        booking.setDriver(oldBooking.getDriver());
        booking.setDateCreated(oldBooking.getDateCreated());
        sessionFactory.getCurrentSession().merge(booking);
        return booking;
    }
    
    public String deleteBooking(Long bookID)
    {
        Booking booking = this.getSingleBooking(bookID);
        sessionFactory.getCurrentSession().delete(booking);
        return "Delete success";
    }
}
