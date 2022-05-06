package com.assignment2.group15.service;

import com.assignment2.group15.entity.Driver;
import com.assignment2.group15.errors.DriverNotExist;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DriverService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Driver> getAllDriver(int page){
        String hql = "from Driver";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        int limit = 10;
        int firstResult=(page-1)*limit;
        query.setFirstResult(firstResult);
        query.setMaxResults(limit);

        return query.list();
    }

    public Driver getSingleDriver(long DriverId){
        String hql = "from Driver i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", DriverId);

        try
        {
            return (Driver) query.getSingleResult();
        }
        catch (Exception e)
        {
            throw new DriverNotExist();
        }
    }

    public Driver saveDriver(Driver driver) {
        sessionFactory.getCurrentSession().save(driver);
        return driver;
    }

    public Driver updateDriver(long driverID, Driver driver) {
        String hql = "from Driver i where i.id=:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", driverID);

        try {
            query.getSingleResult();
        } catch (Exception e) {
            throw new DriverNotExist();
        }

        driver.setId(driverID);
        sessionFactory.getCurrentSession().update(driver);
        return driver;
    }

    public void deleteDriver(long driverID)
    {
        String hql;
        Query query;

        hql = "delete from Driver i where i.id=:id";
        query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", driverID);
        try
        {
            query.executeUpdate();
        }
        catch (Exception e)
        {
            throw new DriverNotExist();
        }
        return;
    }
}
