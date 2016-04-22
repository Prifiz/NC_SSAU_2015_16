/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user.newmodel;

import java.util.Objects;

/**
 *
 * @author андрей
 */
public class User {
    private PrivateInformation privateInformation;
    private ServiceInfo serviceInfo;
    private Address address;

    public User(PrivateInformation privateInformation, ServiceInfo serviceInfo, Address address) {
        this.privateInformation = privateInformation;
        this.serviceInfo = serviceInfo;
        this.address = address;
    }
    
    public User(){
        privateInformation = new PrivateInformation();
        serviceInfo = new ServiceInfo();
        address = new Address();
    }

    public PrivateInformation getPrivateInformation() {
        return privateInformation;
    }

    public void setPrivateInformation(PrivateInformation privateInformation) {
        this.privateInformation = privateInformation;
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object == this) {
            return true;
        }
        if (object.getClass() != this.getClass()) {
            return false;
        }
        User user = (User) object;
        if ((serviceInfo!=null)&&(serviceInfo.getLogin().equals(user.serviceInfo.getLogin()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.privateInformation);
        hash = 31 * hash + Objects.hashCode(this.serviceInfo);
        hash = 31 * hash + Objects.hashCode(this.address);
        return hash;
    }
}
