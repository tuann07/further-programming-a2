package com.assignment2.group15.service;

import com.assignment2.group15.errors.CarNotExist;
import com.assignment2.group15.entity.Car;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Map<String, Long>> getCarUsageInMonth(Integer year, Integer month) {
        String hql =
                "select distinct b.driver.car.id, count(distinct b.pickup) " +
                "from Booking b where b.pickup between :start and :end " +
                "group by b.driver.car.id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusDays(startDate.lengthOfMonth() - 1);

        query.setParameter("start", startDate);
        query.setParameter("end", endDate);

        List<?> queryResult = query.list();

        List<Map<String, Long>> result = new ArrayList<Map<String, Long>>();

        for (int i = 0; i < queryResult.size(); i++) {
            Object[] row = (Object[]) queryResult.get(i);
            result.add(Map.of(
                    "carId", (Long) row[0],
                    "days", (Long) row[1]));
        }

        return result;
    }
}
