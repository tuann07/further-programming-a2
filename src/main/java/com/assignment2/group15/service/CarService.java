package com.assignment2.group15.service;

import com.assignment2.group15.entity.Car;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class CarService
{
    private SessionFactory sessionFactory;

    private static final int PAGE_DEFAULT = 1;
    private static final int LIMIT_DEFAULT = 10;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

	public List<Car> getAllCar(Integer page, Integer limit)
    {
        String hql;
        Query query;
        int pageNumber, limitNumber;

        hql = "from Car";

        query = sessionFactory.getCurrentSession().createQuery(hql);

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

    public Car getSingleCar(Long id)
    {
        Car car = sessionFactory.getCurrentSession().get(Car.class, id);
        
        if (car == null) {
        	throw new NotFoundException("Car with id " + id + " not found");
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
        // keep old properties
        Car oldCar = this.getSingleCar(carID);

        if (car.getVin() == null) car.setVin(oldCar.getVin());
        if (car.getMake() == null) car.setMake(oldCar.getMake());
        if (car.getModel() == null) car.setModel(oldCar.getModel());
        if (car.getColor() == null) car.setColor(oldCar.getColor());
        if (car.getConvertible() == null) car.setConvertible(oldCar.getConvertible());
        if (car.getRating() == null) car.setRating(oldCar.getRating());
        if (car.getLicense() == null) car.setLicense(oldCar.getLicense());
        if (car.getRatePerKm() == null) car.setRatePerKm(oldCar.getRatePerKm());

        car.setDateCreated(oldCar.getDateCreated());
        car.setcarID(carID);

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
                "select distinct b.driver.car.id, count(distinct day(b.pickup)) " +
                "from Booking b where b.pickup > :start and b.pickup < :end " +
                "group by b.driver.car.id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        LocalDate startLD, endLD;

        try {
            startLD = LocalDate.of(year, month, 1);
            // end date gets to the next month because i will get the start time of that day
            // e.g.: 0 a.m of November 1st to 0 a.m of December 1st
            endLD = startLD.plusDays(startLD.lengthOfMonth());
        } catch (DateTimeParseException e) {
            throw new BadRequestException("Invalid date format");
        }

        LocalDateTime startLDT = startLD.atStartOfDay();
        LocalDateTime endLDT = endLD.atStartOfDay().minusSeconds(1);

        query.setParameter("start", startLDT);
        query.setParameter("end", endLDT);

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
