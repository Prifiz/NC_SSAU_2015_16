/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.hibernate.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import team5.datalayer.hibernate.HibernateUtil;
import team5.datamodel.user.adress.City;

/**
 *
 * @author chanta
 */
public class CityDao {
     private static Logger logger = Logger.getLogger(PrivateInformationDao.class);
     
     public void saveCity(City city) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(city);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session=null;
            }
        }
    }

public City getCityById(Integer id) {
        Session session = null;
        City city = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            city = (City) session.get(City.class, id);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session=null;
            }
        }
        return city;
    }

public void deleteCity(City city) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(city);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session=null;
            }
        }
    }
    
}
