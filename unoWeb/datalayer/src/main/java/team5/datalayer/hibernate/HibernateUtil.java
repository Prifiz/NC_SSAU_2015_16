/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.hibernate;

import java.io.File;
import org.apache.log4j.*;
import org.hibernate.HibernateException;
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
            File file = new File("src/main/java/team5/datalayer/hibernate/config/hibernate.cfg.xml");
            sessionFactory = new Configuration().configure(file).buildSessionFactory();
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
        
//    private static SessionFactory configureSessionFactory() {
//
//        // Настройки hibernate
//        Configuration configuration = null;
//        try {
//            configuration = new Configuration()
//                    .setProperty("hibernate.connection.driver_class",
//                            "org.postgresql.Driver")
//                    .setProperty("hibernate.connection.url",
//                            "jdbc:postgresql://localhost:5432/postgres")
//                    .setProperty("hibernate.connection.username",
//                            "postgres")
//                    .setProperty("hibernate.connection.password",
//                            "2536")
//                    .setProperty("hibernate.connection.pool_size", "1")
//                    .setProperty("hibernate.connection.autocommit", "false")
//                    .setProperty("hibernate.cache.provider_class",
//                            "org.hibernate.cache.NoCacheProvider")
//                    .setProperty("hibernate.cache.use_second_level_cache",
//                            "false")
//                    .setProperty("hibernate.cache.use_query_cache", "false")
//                    .setProperty("hibernate.dialect",
//                            "org.hibernate.dialect.PostgreSQLDialect")
//                    .setProperty("hibernate.show_sql", "true")
//                    .setProperty("hibernate.current_session_context_class",
//                            "thread")
//                    //.addPackage( "ru.miralab.db" )
//                    .addAnnotatedClass(team5.datamodel.user.newmodel.PrivateInformation.class)
//                    .addAnnotatedClass(team5.datamodel.user.newmodel.ServiceInfo.class);
//            //.addAnnotatedClass(team5.datamodel.user.adress.Address.class);
//            //serviceRegistry = new ServiceRegistryBuilder().applySettings(
//            //      configuration.getProperties()).buildServiceRegistry();
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
//        }
//        return configuration.buildSessionFactory();
//    }

}
