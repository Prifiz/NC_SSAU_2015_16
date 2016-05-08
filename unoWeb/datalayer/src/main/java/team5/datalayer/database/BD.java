package team5.datalayer.database;

import java.time.LocalDate;
import team5.datalayer.hibernate.dao.PrivateInformationDao;
import team5.datamodel.user.PrivateInformation;
import org.apache.log4j.Logger;
import team5.datalayer.hibernate.dao.DaoFactory;
import team5.datalayer.hibernate.dao.ServiceInfoDao;
import team5.datalayer.hibernate.dao.UserDao;
import team5.datamodel.user.ServiceInfo;
import team5.datamodel.user.User;

/**
 *
 * @author Dmitry
 */
public class BD {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(BD.class);
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
            PrivateInformationDao privateInformationDao = DaoFactory.getPrivateInformationDao();
            PrivateInformation privateInformationTest = new PrivateInformation("Boblogin", "Bob", "Bobov", LocalDate.now());
            privateInformationTest.setUserColomn(new User());
            privateInformationDao.savePrivateInformation(privateInformationTest);
            PrivateInformation privateInformation = privateInformationDao.getPrivateInformationByLogin("Boblogin");
            System.out.println("Инфа по Бобу " + " "+privateInformation.getLogin()+ " " + privateInformation.getName());
            UserDao userDao=DaoFactory.getUserDao();
            User user=new User(privateInformationTest,null,null);
            userDao.saveUser(user);
            User newUser=userDao.getUserByLogin(privateInformationTest.getLogin());
            System.out.println("Инфа о пользователе: "+ " " + newUser.getPrivateInformation().getName());
//            ServiceInfo serviceInfoTest = new ServiceInfo("Boblogin", "bob", "bob@yandex.ru");
//            ServiceInfoDao serviceInfoDao = new ServiceInfoDao();
//            serviceInfoDao.addServiceInfo(serviceInfoTest);
//            ServiceInfo serviceInfo = serviceInfoDao.getServiceInfoByLogin("Boblogin");
//            System.out.println("Сервисная инфа : " + serviceInfo.getLogin() + " " + serviceInfo.getPassword() + " " + serviceInfo.getEmail());

        } catch (ExceptionInInitializerError ex) {
            logger.error(ex.getMessage(), ex);
            System.out.println(ex.getStackTrace().toString());
        }
    }

}
