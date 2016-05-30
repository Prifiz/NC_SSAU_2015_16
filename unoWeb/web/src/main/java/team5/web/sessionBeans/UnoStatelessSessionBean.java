/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.web.sessionBeans;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.ejb.Stateless;
import team5.datalayer.hibernate.dao.AddressDao;
import team5.datalayer.hibernate.dao.PrivateInformationDao;
import team5.datalayer.hibernate.dao.ServiceInfoDao;
import team5.datalayer.hibernate.dao.UserDao;
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

    public UnoStatelessSessionBean() {
    }

    @Override
    public void addUser(String name, String surname, String login, String password, String email, String city, String Country) {

        PrivateInformation privateInformation = new PrivateInformation(login, name, surname);
        privateInformationDao.savePrivateInformation(privateInformation);
        ServiceInfo serviceInfo = new ServiceInfo(login, password, email);
        serviceInfoDao.saveServiceInfo(serviceInfo);
        Address address = new Address(login, city, Country);
        addressDao.saveAddress(address);

    }

    @Override
    public User getUser(String login) {
        PrivateInformation privateInformation = privateInformationDao.getPrivateInformationByLogin(login);
        ServiceInfo serviceInfo = serviceInfoDao.getServiceInfoByLogin(login);
        Address address = addressDao.getAddressByLogin(login);
        User user = new User(privateInformation, address, serviceInfo);
        return user;
    }

    @Override
    public ArrayList<User> findAll() {
        UserDao userDao = new UserDao();
        ArrayList<User> users_dao = (ArrayList<User>) userDao.findAll();
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < users_dao.size(); i++) {
            PrivateInformation privateInformation = privateInformationDao.getPrivateInformationByLogin(users.get(i).getLogin());
            ServiceInfo serviceInfo = serviceInfoDao.getServiceInfoByLogin(users.get(i).getLogin());
            Address address = addressDao.getAddressByLogin(users.get(i).getLogin());
            users.set(i, new User(privateInformation, address, serviceInfo));
            
        }
        return users;
    }

}
