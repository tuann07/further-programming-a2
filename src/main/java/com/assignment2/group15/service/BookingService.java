package com.assignment2.group15.service;

import com.assignment2.group15.errors.BookingNotExist;
import com.assignment2.group15.entity.Booking;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.ZonedDateTime;
import java.util.List;


@Transactional
@Service
public class BookingService
{
	private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    public List<Booking> getAllBooking(Integer page)
    {
        String hql = "from Car";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
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
    public Booking saveBooking(Booking booking)
    {
    	booking.setDateCreated(ZonedDateTime.now());
        sessionFactory.getCurrentSession().save(booking);
        return booking;
    }
    
    public Booking updateBooking(Long bookID, Booking booking)
    {
        this.getSingleBooking(bookID);
        booking.setBookID(bookID);
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
