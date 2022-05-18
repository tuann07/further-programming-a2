package com.assignment2.group15.service;

import com.assignment2.group15.entity.Booking;
import com.assignment2.group15.entity.Customer;
import com.assignment2.group15.entity.Driver;
import com.assignment2.group15.exception.BadRequestException;
import com.assignment2.group15.exception.NotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;


@Transactional
@Service
public class BookingService {
    private SessionFactory sessionFactory;
    private CustomerService customerService;
    private DriverService driverService;
    private CarService carService;

    private static final int PAGE_DEFAULT = 1;
    private static final int LIMIT_DEFAULT = 10;

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
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    public List<Booking> getAllBooking(Integer page, Integer limit, String start, String end)
    {
        String hql;
        LocalDateTime startDate, endDate;
        Query query;
        int pageNumber, limitNumber;

        hql = "from Booking b ";

        if (start != null && end != null) {
            hql += "where b.pickup between :start and :end";
        }

        query = sessionFactory.getCurrentSession().createQuery(hql);

        // filtering
        if (start != null && end != null) {
            try {
                startDate = LocalDate.parse(start).atStartOfDay();
                // get the time with last second of the last day of month
                endDate = LocalDate.parse(end).plusDays(1).atStartOfDay().minusSeconds(1);
            } catch (DateTimeParseException e) {
                throw new BadRequestException("Invalid date format");
            }

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
    public Booking getSingleBooking(Long bookingId)
    {
        Booking booking = sessionFactory.getCurrentSession().get(Booking.class, bookingId);
        
        if (booking == null) {
        	throw new NotFoundException("Booking with id " + bookingId + " not found");
        }
        
        return booking;
    }
    public Booking saveBooking(Booking booking, Long customerId, Long carId)
    {
        Customer customer = customerService.getSingleCustomer(customerId);
        Driver driver = carService.getSingleCar(carId).getDriver();

        if (driver == null) {
            throw new BadRequestException("No driver has registered this vehicle");
        }

        booking.setCustomer(customer);
        booking.setDriver(driver);

        // override date created
    	booking.setDateCreated(ZonedDateTime.now());
        sessionFactory.getCurrentSession().save(booking);
        return booking;
    }
    
    public Booking updateBooking(Long bookingId, Booking booking)
    {
        // keep old properties
        Booking oldBooking = this.getSingleBooking(bookingId);

        if (booking.getStartLoc() == null) booking.setStartLoc(oldBooking.getStartLoc());
        if (booking.getEndLoc() == null) booking.setEndLoc(oldBooking.getEndLoc());
        if (booking.getDistance() == null) booking.setDistance(oldBooking.getDistance());
        if (booking.getPickup() == null) booking.setPickup(oldBooking.getPickup());
        if (booking.getDrop() == null) booking.setDrop(oldBooking.getDrop());

        booking.setCustomer(oldBooking.getCustomer());
        booking.setDriver(oldBooking.getDriver());
        booking.setDateCreated(oldBooking.getDateCreated());
        booking.setId(bookingId);

        // update
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
