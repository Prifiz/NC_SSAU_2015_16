/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import java.time.LocalDate;
import javax.ejb.Stateless;
import team5.datalayer.hibernate.dao.PrivateInformationDao;
import team5.datalayer.hibernate.dao.ServiceInfoDao;
import team5.datamodel.user.PrivateInformation;
import team5.datamodel.user.User;



/**
 *
 * @author chanta
 */
@Stateless
public class UnoStatelessSessionBean implements UnoStatelessSessionBeanRemote {

     private static ServiceInfoDao serviceInfoDao;
     private static PrivateInformationDao privateInformationDao;

    @Override
    public void addUser(String name, String surname, String login, String password, String email, String city, String Country) {
        //todo  вписать те классы модели который используется
        
        PrivateInformation privateInformation = new PrivateInformation(login, name, surname,LocalDate.MAX);
        privateInformationDao.savePrivateInformation(privateInformation);

    }
    
    @Override
    public User getUser(String login) {
        privateInformationDao.getPrivateInformationByLogin(login);
       
        return null;
    }
}
