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
public class Address {
    private String login;
    private int id_city;

    public Address(String login, int id_city) {
        this.login = login;
        this.id_city = id_city;
    }
    public Address(){
        login ="";
        id_city = 0;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId_city() {
        return id_city;
    }

    public void setId_city(int id_city) {
        this.id_city = id_city;
    }
}