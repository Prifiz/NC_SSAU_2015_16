/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user.adress;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author chanta
 */
@XmlType(propOrder = { /*"country", "city"*/}, name = "address")
@XmlRootElement
public class Address implements Serializable {

    private Integer idCity;
    private String login;

    public Address(Integer idCity, String city) {
        this.idCity = idCity;
        this.login = city;
    }

    public Address() {
        login = new String();
        idCity = -1;
    }

    public String getLogin() {
        return login;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
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
        Address address = (Address) object;
        if ((login!=null)&&(login.equals(address.getLogin())) &&(idCity!=null)&& (idCity.equals(address.getIdCity()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 37 * result + login.hashCode() + idCity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(10);
        builder.append(" Address: ").append(login).append(", ").append(idCity).append(" ");
        return builder.toString();
    }

}
