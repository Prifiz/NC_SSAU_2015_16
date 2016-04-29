/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user.newmodel;

/**
 *
 * @author андрей
 */
public class PrivateInformation {
    private String login;
    private String name;
    private String surname;

    public PrivateInformation(String login, String name, String surname) {
        this.login = login;
        this.name = name;
        this.surname = surname;
    }
    
    public PrivateInformation(){
        login = "";
        name = "";
        surname="";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
