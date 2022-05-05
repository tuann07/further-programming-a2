package com.assignment2.group15.service;

import com.assignment2.group15.errors.CarNotExist;
import com.assignment2.group15.entity.Car;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Transactional
@Service
public class CarService
{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    
	public List<Car> getAllCar(Integer page)
    {
        String hql = "from Car";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public Car getSingleCar(Long id)
    {
        Car car = sessionFactory.getCurrentSession().get(Car.class, id);
        
        if (car == null)
        {
        	throw new CarNotExist();
        }
        
        return car;
    }

    public Car saveCar(Car car)
    {
    	car.setDateCreated(ZonedDateTime.now());
        sessionFactory.getCurrentSession().save(car);
        return car;
    }

    public Car updateCar(Long id, Car car)
    {
        this.getSingleCar(id);
        car.setId(id);
        sessionFactory.getCurrentSession().merge(car);
        return car;
    }

    public String deleteCar(Long id)
    {
    	Car car = this.getSingleCar(id);
    	sessionFactory.getCurrentSession().delete(car);
        return "Delete success";
    }


}
