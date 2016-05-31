/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user;

import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import team5.datamodel.transmissions.LocalDateAdapter;

/**
 *
 * @author chanta
 */
@XmlType(propOrder = {/* "name", "surname", "bDay"*/}, name = "privateInformation")
@XmlRootElement
@Entity
@Table(name = "private_information")
public class PrivateInformation implements Serializable {

    @Id
    @Column(name="login")
   // @Transient
    private String login;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_col")
    private User userColomn;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;
    
    @Column(name="b_date")
    private LocalDate bDay;

    public PrivateInformation(String login, String name, String surname) {
        this.name = name;
        this.login = login;
        this.surname = surname;
        this.bDay = LocalDate.now();
    }

    public PrivateInformation() {
        this.name = "";
        this.surname = "";
        this.bDay = LocalDate.now();
        this.login = "";
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getbDay() {
        return bDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUserColomn() {
        return userColomn;
    }
    
    public void setUserColomn(User userColomn) {
        this.userColomn = userColomn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setbDay(LocalDate bDay) {
        this.bDay = bDay;
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
        PrivateInformation privateInformation = (PrivateInformation) object;
        if ((login != null) && (login.equals(privateInformation.getLogin())) && (name != null) && (name.equals(privateInformation.getName())) && (surname != null) && (surname.equals(privateInformation.getSurname()))
                && (bDay != null) && (bDay.equals(privateInformation.bDay))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(10);
        builder.append(" ").append(name).append(", ").append(surname).append(", ")
                .append(bDay).append(", ").append(login);
        return builder.toString();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 37 * result + name.hashCode() + surname.hashCode() + bDay.hashCode() + login.hashCode();
        return result;
    }

}
