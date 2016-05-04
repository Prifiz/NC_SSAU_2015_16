package team5.datalayer.database;



import team5.datalayer.hibernate.dao.PrivateInformationDao;
import team5.datamodel.user.newmodel.PrivateInformation;
import org.apache.log4j.Logger;

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
            PrivateInformationDao privateInformationDao = new PrivateInformationDao();
            PrivateInformation privateInformation = privateInformationDao.getPrivateInformationByLogin("Bob");
            System.out.println("Инфа по Бобу " + privateInformation.getLogin() + " " + privateInformation.getName());
        } catch (ExceptionInInitializerError ex) {
            logger.error(ex.getMessage(),ex);
            System.out.println(ex.getStackTrace().toString());
        }
    }

}

