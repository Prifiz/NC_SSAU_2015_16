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

/**
 *
 * @author chanta
 */
public class CardDao {

    private static Logger logger = Logger.getLogger(CardDao.class);

    public void saveCard(Card card) {
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
        Card card = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            card = (Card) session.get(Card.class, cardId);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session = null;
            }
        }
        return card;
    }

    public void deleteCard(Card card) {
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
