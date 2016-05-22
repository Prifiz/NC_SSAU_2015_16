/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user.adress;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author андрей
 */
@Entity
@Table(name = "city")
public class City {

    @Id @GeneratedValue
    @Column(name = "id_city")
    private Integer idCity;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "id_country")
    private Integer idCountry;

    public City() {
        cityName = "";
    }

    public City(String cityName) {
        this.cityName = cityName;
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

    public Integer getIdCity() {
        return idCity;
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
        if ((cityName != null) && (cityName.equals(city.getCityName())) && (idCity != null) && (idCity.equals(city.getIdCity()))
                && (idCountry != null) && (idCountry.equals(city.getIdCountry()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 37 * result + cityName.hashCode() + idCity.hashCode() + idCountry.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(10);
        builder.append(" City: ").append(cityName).append(", ").append(idCity).append(" ").append(idCountry).append(" ");
        return builder.toString();
    }

}
