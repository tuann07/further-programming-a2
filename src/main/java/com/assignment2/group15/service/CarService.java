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

    
    public List<Car> getAllCar(int page)
    {
        String hql = "from Car";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        //allow paging

        int limit = 10;
        //limit results to 10 for each page

        //index of the first result of each page
        int firstResult=(page-1)*limit;

        query.setFirstResult(firstResult);
        query.setMaxResults(limit);

        return query.list();
    }

    public Car getSingleCar(long carID)
    {
        Car car = sessionFactory.getCurrentSession().get(Car.class, carID);
        
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

    public Car updateCar(long carID, Car car)
    {
        this.getSingleCar(carID);
        car.setId(carID);
        sessionFactory.getCurrentSession().update(car);
        return car;
    }

    public String deleteCar(long carID)
    {
    	Car car = this.getSingleCar(carID);
    	sessionFactory.getCurrentSession().delete(car);
        return "Delete success";
    }


}
