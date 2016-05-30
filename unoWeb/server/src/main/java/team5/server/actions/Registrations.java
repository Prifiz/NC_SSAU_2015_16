/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import java.time.LocalDate;
import team5.datalayer.hibernate.dao.AddressDao;
import team5.datalayer.hibernate.dao.PrivateInformationDao;
import team5.datalayer.hibernate.dao.ServiceInfoDao;
import team5.datalayer.hibernate.dao.UserDao;
import team5.datamodel.actions.WorkUser;
import team5.datamodel.exceptions.*;
import team5.datamodel.user.PrivateInformation;
import team5.datamodel.user.ServiceInfo;
import team5.datamodel.user.User;
import team5.datamodel.user.adress.Address;

/**
 *
 * @author chanta
 */
public class Registrations {

    public void registrationUser(PrivateInformation privateInformation, ServiceInfo serviceInfo, Address address, User user){
        PrivateInformationDao pi = new PrivateInformationDao();
        pi.savePrivateInformation(privateInformation);
        ServiceInfoDao si = new ServiceInfoDao();
        si.saveServiceInfo(serviceInfo);
        AddressDao ad = new AddressDao();
        ad.saveAddress(address);
        UserDao us = new UserDao();
        us.saveUser(user);
    }

//    private boolean checkOfExistenceOfUser(User user){
//        for (User u : users) {
//            if (u.getServiceInfo().equals(user.getServiceInfo()))
//                break;
//        }
//        return false;
//    }
}
