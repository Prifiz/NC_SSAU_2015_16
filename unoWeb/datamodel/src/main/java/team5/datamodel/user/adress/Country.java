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
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "id_country")
    private Integer idCountry;

    @Column(name = "country_name")
    private String countryName;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Country() {
        countryName = "";
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
        Country country = (Country) object;
        if ((countryName != null) && (countryName.equals(country.getCountryName())) && (idCountry != null) && (idCountry.equals(country.getIdCountry()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 37 * result + countryName.hashCode() + idCountry.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(10);
        builder.append(" City: ").append(countryName).append(", ").append(idCountry).append(" ");
        return builder.toString();
    }

}
