/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user.adress;

import team5.datamodel.user.newmodel.*;

/**
 *
 * @author андрей
 */
public class City {
    private String cityName;
    private Integer idCountry;
    private Integer idCity;

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

   
    
    public City(){
        cityName = "";
        idCountry = -1;
        idCity=-1;
    }

    public City(String cityName, Integer idCountry, Integer idCity) {
        this.cityName = cityName;
        this.idCountry = idCountry;
        this.idCity = idCity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
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
        City city = (City) object;
        if ((cityName!=null)&&(cityName.equals(city.getCityName())) &&(idCity!=null)&& (idCity.equals(city.getIdCity()))
                &&(idCountry!=null)&& (idCountry.equals(city.getIdCountry()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 37 * result + cityName.hashCode() + idCity.hashCode()+idCountry.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(10);
        builder.append(" City: ").append(cityName).append(", ").append(idCity).append(" ").append(idCountry).append(" ");
        return builder.toString();
    }

}
