/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.hibernate.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import team5.datalayer.hibernate.HibernateUtil;

import team5.datamodel.user.User;

/**
 *
 * @author андрей
 */
public class UserDao {

    private static Logger logger = Logger.getLogger(UserDao.class);

    public void saveUser(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
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

    public Collection findAll() {
        Session session = null;
        List users = new ArrayList<User>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(User.class).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session = null;
            }
        }
        return users;
    }

    public User getUserByLogin(String login) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.get(User.class, login);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session = null;
            }
        }
        return user;
    }

//    public void updateUser(User user) {
//        Session session = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.update(user);
//        } catch (Exception ex) {
//            logger.debug(ex.getMessage());
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }
    public void deleteUser(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
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
