package com.assignment2.group15.service;

import com.assignment2.group15.entity.Car;
import com.assignment2.group15.entity.Driver;
import com.assignment2.group15.exception.DriverNotExist;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Transactional
@Service
public class DriverService {

    private SessionFactory sessionFactory;
    private CarService carService;

    private static final int PAGE_DEFAULT = 1;
    private static final int LIMIT_DEFAULT = 10;

    @Autowired
    public void setCarService(CarService carService)
    {
        this.carService = carService;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public List<Driver> getAllDriver(Integer page, Integer limit, String name, String address, String phone){
        String hql;
        Query query;
        int pageNumber, limitNumber;
        boolean isSearch = false;

        hql = "from Driver d ";

        if (name != null || address != null || phone != null) {
            hql += "where ";

            if (name != null) {
                hql += "d.name = :name ";
                isSearch = true;
            }
            if (address != null) {
                hql += (isSearch ? "and " : "") + "d.address = :address ";
                isSearch = true;
            }
            if (phone != null) {
                hql += (isSearch ? "and " : "") + "d.phone = :phone";
            }
        }

        query = sessionFactory.getCurrentSession().createQuery(hql);

        if (name != null) {
            query.setParameter("name", name);
        }
        if (address != null) {
            query.setParameter("address", address);
        }
        if (phone != null) {
            query.setParameter("phone", phone);
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

    public Driver getSingleDriver(Long driverId){
        Driver driver = sessionFactory.getCurrentSession().get(Driver.class, driverId);


        if (driver == null) {
            throw new DriverNotExist();
        }

        return driver;
    }

    public Driver saveDriver(Driver driver, Long carId)
    {
        Car car = carService.getSingleCar(carId);
        driver.setCar(car);
        driver.setDateCreated(ZonedDateTime.now());
        sessionFactory.getCurrentSession().save(driver);
        return driver;
    }

    public Driver updateDriver(Driver driver, Long driverId, Long carId) {
        // keep old properties
        Driver oldDriver = this.getSingleDriver(driverId);
        driver.setDateCreated(oldDriver.getDateCreated());
        driver.setCar(oldDriver.getCar());

        if (carId != null) {
            Car car = carService.getSingleCar(carId);
            driver.setCar(car);
        }

        driver.setId(driverId);
        sessionFactory.getCurrentSession().merge(driver);
        return driver;
    }

    public String deleteDriver(Long driverId)
    {
        Driver driver = this.getSingleDriver(driverId);
        sessionFactory.getCurrentSession().delete(driver);
        return "Driver deleted";
    }
}
