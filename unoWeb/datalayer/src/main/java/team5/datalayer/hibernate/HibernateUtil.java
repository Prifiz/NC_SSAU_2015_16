/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author андрей
 */
public class HibernateUtil {

//    private Session session;
//    private static SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
//    static {
        try {
              Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
           StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
//             String configFile = "hibernate.cfg.xml";
//           return new Configuration().configure(configFile).buildSessionFactory();


//            Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
//            cfg.addAnnotatedClass(team5.datamodel.user.PrivateInformation);
//            
////        String configFile = "hibernate.cfg.xml";
//            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").applySettings(cfg.getProperties()).build();
//
//            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();

            //return metadata.getSessionFactoryBuilder().build();

//    static {
//        try {
//            
//            
//            String configFile = "hibernate.cfg.xml";
////            sessionFactory = new Configuration().configure().buildSessionFactory();
//            sessionFactory = new Configuration().configure(configFile).buildSessionFactory();
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
