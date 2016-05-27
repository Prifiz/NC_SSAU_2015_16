/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.hibernate.dao;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import team5.datalayer.hibernate.HibernateUtil;
import team5.datamodel.card.Card;
import team5.datamodel.card.NumericCard;
import team5.datamodel.user.User;

/**
 *
 * @author chanta
 */
public class NumericCardDao {

    private static Logger logger = Logger.getLogger(NumericCardDao.class);
    
    public ArrayList<User> findAll() {
        Session session = null;
        ArrayList<User> users = new ArrayList() ;
        try {
        Query query = HibernateUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Abilities a");
        users = (ArrayList<User> ) query.list();
        
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session=null;
            }
        }
        return users;
    }

    public void saveNumericCard(NumericCard numericCard) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(numericCard);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session = null;
            }
        }
    }

    public NumericCard getNumericCardById(Integer cardId) {
        Session session = null;
        NumericCard numericCard = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            numericCard = (NumericCard) session.get(NumericCard.class, cardId);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session = null;
            }
        }
        return numericCard;
    }

    public void deleteNumericCard(NumericCard numericCard) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(numericCard);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session = null;
            }
        }
    }

}
