/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user.adress;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author chanta
 */
@XmlType(propOrder = { /*"country", "city"*/}, name = "address")
@XmlRootElement
@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "city")
    private String city;
    
    @Column(name = "country")
    private String country;
    

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public Address() {
        city = new String();
        country = new String();
    }

    

    public String getLogin() {
        return login;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    

    public void setLogin(String login) {
        this.login = login;
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
        if ((login != null) && (login.equals(address.getLogin())) && (city.equals(address.getCity())) && (country.equals(address.getCountry()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 37 * result + login.hashCode() + city.hashCode()+country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(10);
        builder.append(" Address: ").append(login).append(", ").append(city).append(" ").append(", ").append(country).append(" ");
        return builder.toString();
    }

}
