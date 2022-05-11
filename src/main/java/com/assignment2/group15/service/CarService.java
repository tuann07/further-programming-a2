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

    
	public List<Car> getAllCar(Integer page, String start, String end)
    {
        String hql;
        LocalDate startDate, endDate;
        Query query;
        int pageNumber;

        hql = "from Car i";
        if (start!=null || end!= null) {
            hql += "where i.pickUpTime bewteen :start and :end";
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

    public Car updateCar(Long carID, Car car)
    {
        Car oldCar = this.getSingleCar(carID);
        car.setcarID(carID);
        car.setDateCreated(oldCar.getDateCreated());
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
