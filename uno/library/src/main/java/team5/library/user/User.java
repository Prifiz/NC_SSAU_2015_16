/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.library.user;

import team5.library.user.adress.Address;
import team5.library.user.adress.AddressInitializer;
import team5.library.user.adress.SimpleAddressInitializer;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author chanta
 */
@XmlType(propOrder = {/* "privateInformation", "address", "serviceInfo"*/ }, name = "User")
@XmlRootElement
public class User implements Serializable {

    private PrivateInformation privateInformation;
    AddressInitializer addressInitializer = new SimpleAddressInitializer();
    private Address address = addressInitializer.initDefaultAddress();
    private ServiceInfo serviceInfo;

    public User() {
        serviceInfo=new ServiceInfo();
        address = new Address();
        privateInformation=new PrivateInformation();
    }

    public User(PrivateInformation privateInformation, Address address, ServiceInfo serviceInfo) {
        this.privateInformation = privateInformation;
        this.address = address;
        this.serviceInfo = serviceInfo;
    }

    public PrivateInformation getPrivateInformation() {
        return privateInformation;
    }

    public Address getAddress() {
        return address;
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setPrivateInformation(PrivateInformation privateInformation) {
        this.privateInformation = privateInformation;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
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
        if (user.serviceInfo.getLogin().equals(serviceInfo.getLogin())){
        //if (serviceInfo.getLogin().equals(user.serviceInfo.getLogin())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User: ").append(privateInformation.toString()).append(", ").append(serviceInfo.toString())
                .append(", ").append(address.toString()).append("\n");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return address.hashCode() + serviceInfo.hashCode() + privateInformation.hashCode();
    }
}
