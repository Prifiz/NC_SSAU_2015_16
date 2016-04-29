/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user.newmodel;

import java.time.LocalDate;

/**
 *
 * @author андрей
 */
public class ServiceInfo {

    private String login;
    private String password;
    private String email;
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
