/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user;

import team5.datamodel.user.adress.Address;
import team5.datamodel.user.adress.AddressInitializer;
import team5.datamodel.user.adress.SimpleAddressInitializer;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author chanta
 */
@XmlType(propOrder = {/* "privateInformation", "address", "serviceInfo"*/}, name = "User")
@XmlRootElement
@Entity
@Table(name = "user_table")
public class User implements Serializable {


    @Id
    @Column(name="login")
    private String login;

    @OneToOne(mappedBy = "userColomn")
    private PrivateInformation privateInformation;

    @Transient
    AddressInitializer addressInitializer = new SimpleAddressInitializer();

    @OneToOne(mappedBy = "userAdressColomn")
    private Address address = addressInitializer.initDefaultAddress();

    @OneToOne(mappedBy = "userServiceInfoColomn")
    private ServiceInfo serviceInfo;

    public User() {
        serviceInfo = new ServiceInfo();
        address = new Address();
        privateInformation = new PrivateInformation();
    }

    public User(PrivateInformation privateInformation, Address address, ServiceInfo serviceInfo) {
        this.privateInformation = privateInformation;
        this.address = address;
        this.serviceInfo = serviceInfo;
        this.login = privateInformation.getLogin();
    }

    public PrivateInformation getPrivateInformation() {
        return privateInformation;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        if ((serviceInfo != null) && (serviceInfo.getLogin().equals(user.serviceInfo.getLogin()))) {
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
