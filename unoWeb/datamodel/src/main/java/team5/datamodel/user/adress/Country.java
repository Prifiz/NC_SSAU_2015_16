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
public class Country {

    private Integer idCountry;
    private String countryName;

    public Country(Integer idCountry, String countryName) {
        this.idCountry = idCountry;
        this.countryName = countryName;
    }

    public Country() {
        countryName = "";
        idCountry = -1;
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
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
