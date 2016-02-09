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

/**
 *
 * @author chanta
 */

@XmlType(propOrder = {/*"login", "password","dateOfRegistration", "email"*/}, name = "serviceInfo")
@XmlRootElement
public class ServiceInfo implements Serializable {

    private String login;
    private String password;
    private final LocalDate dateOfRegistration;
    private String email;

    public ServiceInfo(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.dateOfRegistration = LocalDate.now();
        this.email = email;
    }

    public ServiceInfo() {
        this.login="";
        this.password = "";
        this.dateOfRegistration = LocalDate.now();
        this.email = "";
    }
    

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
        //TODO дата рождения еще должна быть где то
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
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
        ServiceInfo serviceInfo = (ServiceInfo) object;
        if ((login!=null)&&(login.equals(serviceInfo.getLogin())) && (password!=null)&&(password.equals(serviceInfo.getPassword()))
                &&(dateOfRegistration!=null)&& (dateOfRegistration.equals(serviceInfo.getDateOfRegistration())) && (email!=null)&&(email.equals(serviceInfo.email))) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 37 * result + login.hashCode() + password.hashCode();
        result += 37 * result + email.hashCode() + dateOfRegistration.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(10);
        builder.append(" ServiceInfo: ").append(login).append(", ").append(password).append(", ")
                .append(email).append(", ").append(dateOfRegistration).append(" ");
        return builder.toString();
    }

}
