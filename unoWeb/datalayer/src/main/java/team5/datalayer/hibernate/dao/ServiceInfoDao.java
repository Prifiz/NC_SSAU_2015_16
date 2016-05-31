/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.hibernate.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import team5.datalayer.hibernate.HibernateUtil;
import team5.datamodel.user.ServiceInfo;


/**
 *
 * @author андрей
 */
public class ServiceInfoDao {
     private static Logger logger = Logger.getLogger(ServiceInfoDao.class);
    public void saveServiceInfo(ServiceInfo serviceInfo){
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(serviceInfo);
            session.getTransaction().commit();
        }catch(Exception ex){
            logger.debug(ex.getMessage());
        }finally{
            if(session!=null && session.isOpen()){
                session.close();
                session=null;
            }
        }
    }
    
    
//    Session session = null;
//        PrivateInformation privateInformation = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            privateInformation = (PrivateInformation) session.get(PrivateInformation.class, login);
//        } catch (Exception ex) {
//            logger.debug(ex.getMessage());
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//                session=null;
//            }
//        }
//        return privateInformation;
//    }
    public ServiceInfo getServiceInfoByLogin(String login){
        Session session=null;
        ServiceInfo serviceInfo = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            serviceInfo = (ServiceInfo) session.get(ServiceInfo.class, login);
        }catch(Exception ex){
            logger.debug(ex.getMessage());
        }finally{
            if(session!=null && session.isOpen()){
                session.close();
                session=null;
            }
        }
        return serviceInfo;
    }
//    public void updateServiceInfo(ServiceInfo serviceInfo) {
//        Session session = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.update(serviceInfo);
//        } catch (Exception ex) {
//            logger.debug(ex.getMessage());
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }
//    
    public void deletePrivateInformation(ServiceInfo serviceInfo){
         Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(serviceInfo);
            session.getTransaction().commit();
        }catch(Exception ex){
            logger.debug(ex.getMessage());
        }finally{
            if(session!=null && session.isOpen()){
                session.close();
                session=null;
            }
        }
    }
}
