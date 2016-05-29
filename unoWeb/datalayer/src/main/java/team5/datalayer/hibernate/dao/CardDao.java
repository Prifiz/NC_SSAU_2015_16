/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.hibernate.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import team5.datalayer.hibernate.HibernateUtil;
import team5.datamodel.card.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author chanta
 */
public class CardDao {

    private static Logger logger = Logger.getLogger(CardDao.class);
    
    public Collection findAll() {
        Session session = null;
        List сards = new ArrayList<Card>() ;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
            сards = session.createCriteria(Card.class).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session=null;
            }
        }
        return сards;
    }

    public void saveNumericCard(Card card) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(card);
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

    public Card getCardById(Integer cardId) {
        Session session = null;
        Card numericCard = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            numericCard = session.get(Card.class, cardId);
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

    public void deleteNumericCard(Card card) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(card);
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
