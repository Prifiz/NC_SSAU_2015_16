/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author андрей
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(HibernateUtil.class);

    static {
        try {
            String configFile = "hibernate.cfg.xml";
            sessionFactory = new Configuration().configure(configFile).buildSessionFactory();
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
