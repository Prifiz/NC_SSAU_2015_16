/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.database;

import org.apache.log4j.Logger;
import team5.datalayer.hibernate.HibernateUtil;
import team5.datalayer.hibernate.dao.PrivateInformationDao;
import team5.datamodel.user.newmodel.PrivateInformation;

/**
 *
 * @author Dmitry
 */
public class BD {

    public static void main(String[] args) {

//        Logger logger;
        // Creator creator = new Creator();
        //Droper droper=new Droper();
        // Searcher searcher=new Searcher();
        // DataBaseWorker dbworker = new DataBaseWorker();
        //  dbworker.openConnectionDataBase();
        //creator.createAddresses();
        //  droper.dropDataBase();
        // dbworker.closeConnectionDataBase();
        //creator.createAddresses();
        //System.out.println(searcher.search("Bob"));
        try {
            PrivateInformationDao privateInformationDao = new PrivateInformationDao();
            PrivateInformation privateInformation = privateInformationDao.getPrivateInformationByLogin("Bob");
            System.out.println("Инфа по Бобу " + privateInformation.getLogin() + " " + privateInformation.getName());
        } catch (ExceptionInInitializerError ex) {
//            logger.debug(ex.getMessage());
            System.out.println("Ошибка!!!!! " + ex.getMessage());
        }
    }

}
