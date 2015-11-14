/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user;


/**
 *
 * @author chanta
 */
public class User {
    private PrivateInformation privateInformation;
    private Address address;
    private ServiceInfo serviceInfo; 
    
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
        if (object ==null)
            return false;
         
        if (object == this) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User user =(User)object;
        
        if ((privateInformation.equals(user.getPrivateInformation()))&&(address.equals(user.address))
                &&(serviceInfo.equals(user.serviceInfo))){
            return true;
        }
          return false;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("User: ").append(privateInformation.toString()).append(", ")
                .append(", ").append(address.toString()).append("\n");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return  address.hashCode()+serviceInfo.hashCode()+privateInformation.hashCode();
    }
    
    

    
    
}
