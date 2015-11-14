/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user;


import java.time.LocalDate;//????
//import org.joda.time.*;


/**
 *
 * @author chanta
 */
public class ServiceInfo {
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

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
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
        if (object ==null)
            return false;
         
        if (object == this) {
            return true;
        }
        if (!(object instanceof ServiceInfo)) {
            return false;
        }
        ServiceInfo info =(ServiceInfo)object;
        if ((this.getLogin().compareTo(info.getLogin())==0)&&(this.password.compareTo(info.getPassword())==0)
            &&(this.dateOfRegistration.equals(info.getDateOfRegistration()))&&(this.email.compareTo(info.email))==0)
            return true;
        
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + this.getLogin().hashCode()+this.getPassword().hashCode();
        result = 37 * result + this.getEmail().hashCode()+this.getDateOfRegistration().hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder buffer=new StringBuilder();
        buffer.append(" ServiceInfo: ").append(getLogin()).append(", ").append(getPassword()).append(", ")
                .append(getEmail()).append(" ").append(getDateOfRegistration()).append(" ");
        return buffer.toString();
    }
    
    
            
    
}
