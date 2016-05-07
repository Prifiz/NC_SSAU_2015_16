/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user.newmodel;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author андрей
 */
//@Entity
//@Table(name = "servise_info")//Граматеи:D service - правильный вариант
public class ServiceInfo implements Serializable{

    @Id
    @OneToOne
    @PrimaryKeyJoinColumn(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "data_of_registration")
    //@Temporal(value = TemporalType.DATE)
    private LocalDate dateOfRegistration;

    public ServiceInfo(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.dateOfRegistration = LocalDate.now();
    }

    public ServiceInfo() {
        login = "";
        password = "";
        email = "";
        dateOfRegistration = LocalDate.now();

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
}
