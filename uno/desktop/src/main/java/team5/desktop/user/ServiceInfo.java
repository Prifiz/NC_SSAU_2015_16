/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user;

import java.time.LocalDate;


/**
 *
 * @author chanta
 */
public class ServiceInfo implements java.io.Serializable{
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
        if ((login.equals(info.getLogin()))&&(password.equals(info.getPassword()))
            &&(dateOfRegistration.equals(info.getDateOfRegistration()))&&(email.equals(info.email)))
            return true;
        
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 37 * result + login.hashCode()+password.hashCode();
        result += 37 * result + email.hashCode()+dateOfRegistration.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder(10);
        builder.append(" ServiceInfo: ").append(login).append(", ").append(password).append(", ")
                .append(email).append(" ").append(dateOfRegistration).append(" ");
        return builder.toString();
    }
    
    
            
    
}
