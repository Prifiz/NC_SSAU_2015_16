/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.hibernate.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import team5.datalayer.hibernate.HibernateUtil;

import team5.datamodel.user.PrivateInformation;

/**
 *
 * @author андрей
 */
public class PrivateInformationDao {

    private static Logger logger = Logger.getLogger(PrivateInformationDao.class);

    public void savePrivateInformation(PrivateInformation privateInformation) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(privateInformation);
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

    public PrivateInformation getPrivateInformationByLogin(String login) {
        Session session = null;
        PrivateInformation privateInformation = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            privateInformation = (PrivateInformation) session.get(PrivateInformation.class, login);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                session=null;
            }
        }
        return privateInformation;
    }

//    public void updatePrivateInformation(PrivateInformation privateInformation) {
//        Session session = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.update(privateInformation);
//        } catch (Exception ex) {
//            logger.debug(ex.getMessage());
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }

    public void deletePrivateInformation(PrivateInformation privateInformation) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(privateInformation);
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
