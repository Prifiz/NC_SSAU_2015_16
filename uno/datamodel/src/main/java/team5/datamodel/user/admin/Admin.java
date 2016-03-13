/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user.admin;

import team5.datamodel.user.PrivateInformation;
import team5.datamodel.user.ServiceInfo;
import team5.datamodel.user.User;
import team5.datamodel.user.adress.Address;
import team5.datamodel.constants.Constants;

/**
 *
 * @author chanta
 */
public final class Admin extends User {

    public Admin(PrivateInformation privateInformation, Address address, ServiceInfo serviceInfo) {
        super(privateInformation, address, serviceInfo);
    }

    public Admin() {
        //super(privateInformation, address, serviceInfo);
        super.getServiceInfo().setLogin(Constants.LOGIN_ADMIN);
        super.getServiceInfo().setPassword(Constants.PASSWORD_ADMIN);
        super.getServiceInfo().setEmail(Constants.EMAIL_ADMIN);
        super.getAddress().setCity(Constants.CITY_ADMIN);
        super.getAddress().setCountry(Constants.COUNTRY_ADMIN);
        super.getPrivateInformation().setName(Constants.NAME_ADMIN);
        super.getPrivateInformation().setSurname(Constants.SURNAME_ADMIN);
        super.getPrivateInformation().setbDay(Constants.B_DAY_ADMIN);
    }
    
    @Override
    public void setServiceInfo(ServiceInfo serviceInfo) {
        getServiceInfo().setLogin(Constants.LOGIN_ADMIN);
        getServiceInfo().setPassword(Constants.PASSWORD_ADMIN);
        getServiceInfo().setEmail(serviceInfo.getEmail());
    }
    
}
