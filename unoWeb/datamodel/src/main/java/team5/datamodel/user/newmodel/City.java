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
public class City {
    private String cityName;
    private int id_country;

    public City(String city_name, int id_country) {
        this.cityName = city_name;
        this.id_country = id_country;
    }
    
    public City(){
        cityName = "";
        id_country = 0;
    }

    public int getId_country() {
        return id_country;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }

    public String getCity_name() {
        return cityName;
    }

    public void setCity_name(String city_name) {
        this.cityName = city_name;
    }
}
