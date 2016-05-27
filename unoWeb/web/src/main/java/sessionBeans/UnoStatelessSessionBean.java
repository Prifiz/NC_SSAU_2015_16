/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import java.time.LocalDate;
import javax.ejb.Stateless;
import team5.datalayer.hibernate.dao.AddressDao;
import team5.datalayer.hibernate.dao.PrivateInformationDao;
import team5.datalayer.hibernate.dao.ServiceInfoDao;
import team5.datamodel.user.PrivateInformation;
import team5.datamodel.user.ServiceInfo;
import team5.datamodel.user.User;
import team5.datamodel.user.adress.Address;



/**
 *
 * @author chanta
 */
@Stateless
public class UnoStatelessSessionBean implements UnoStatelessSessionBeanRemote {

     private static ServiceInfoDao serviceInfoDao;
     private static PrivateInformationDao privateInformationDao;
     private static AddressDao addressDao;

    @Override
    public void addUser(String name, String surname, LocalDate BDay,String login, String password, String email, String city, String Country) {
        
        PrivateInformation privateInformation = new PrivateInformation(login, name, surname,BDay);
        privateInformationDao.savePrivateInformation(privateInformation);
        ServiceInfo serviceInfo = new ServiceInfo(login, password, email);
        serviceInfoDao.saveServiceInfo(serviceInfo);
        Address address = new Address(login, city, Country);
        addressDao.saveAddress(address);

    }
    
    @Override
    public User getUser(String login) {
        PrivateInformation privateInformation  =privateInformationDao.getPrivateInformationByLogin(login);
        ServiceInfo serviceInfo = serviceInfoDao.getServiceInfoByLogin(login);
        Address address = addressDao.getAddressByLogin(login);
        User user = new User(privateInformation, address, serviceInfo);
        return user;
    }
}
