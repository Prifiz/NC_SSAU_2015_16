/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.user;

import java.time.LocalDate;
import java.io.Serializable;
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
public class PrivateInformation implements Serializable {

    private String name;
    private String surname;
    private LocalDate bDay;

    public PrivateInformation(String name, String surname, LocalDate bDay) {
        this.name = name;
        this.surname = surname;
        this.bDay = bDay;
    }

    public PrivateInformation() {
        this.name = "";
        this.surname = "";
        this.bDay = LocalDate.now();
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
        if ((name!= null)&&(name.equals(privateInformation.getName())) &&(surname!=null)&& (surname.equals(privateInformation.getSurname()))
                && (bDay!=null)&&(bDay.equals(privateInformation.bDay))){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(10);
        builder.append(" ").append(name).append(", ").append(surname).append(", ")
                .append(bDay);
        return builder.toString();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 37 * result + name.hashCode() + surname.hashCode() + bDay.hashCode();
        return result;
    }

}
