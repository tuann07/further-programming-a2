package com.assignment2.group15.services;

import com.assignment2.group15.errors.CarNotExist;
import com.assignment2.group15.entity.Car;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    //due to having similar idea, codes in Service & Controller of other entities
    //are very similar, just different variables

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
        String hql = "from Car i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", carID);

        try
        {
            return (Car) query.getSingleResult();
        }
        catch (Exception e)
        {
            throw new CarNotExist();
        }
    }

    public Car saveCar(Car car)
    {
        sessionFactory.getCurrentSession().save(car);
        return car;
    }

    public Car updateCar(long carID, Car car)
    {
        String hql = "from Car i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", carID);

        try
        {
            query.getSingleResult();
        }
        catch (Exception e)
        {
            throw new CarNotExist();
        }

        car.setId(carID);
        sessionFactory.getCurrentSession().update(car);
        return car;
    }

    public void deleteCar(long carID)
    {
        String hql;
        Query query;

        hql = "delete from Car i where i.id=:id";
        query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", carID);
        try
        {
            query.executeUpdate();
        }
        catch (Exception e)
        {
            throw new CarNotExist();
        }
        return;
    }


}
