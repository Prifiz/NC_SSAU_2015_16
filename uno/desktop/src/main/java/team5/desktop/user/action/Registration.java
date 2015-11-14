/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user.action;

import java.time.LocalDate;
import java.util.ArrayList;
import team5.desktop.user.User;

/**
 *
 * @author chanta
 */
public class Registration {
    
    public Boolean registrationUser(String name, String surname, String country, String sity, String login, String password, String email, LocalDate bDay){
//        if(checkOfExistenceOfUser(user))
//            System.out.println("This user has already been created");
//        
       // WorkUser workUser= new WorkUser();
         WorkUser workUser = WorkUser.getWork();
         
        if ((name.compareTo("")==0)||(surname.compareTo("")==0)||(login.compareTo("")==0)||(password.compareTo("")==0)||(email.compareTo("")==0))
            return false;
        else{
            workUser.addUser(name, surname, country, sity, login, password, email, bDay);
            return true;
        }
        
    }
    
//    private boolean checkOfExistenceOfUser(User user){
//        for (User u : users) {
//            if (u.getServiceInfo().equals(user.getServiceInfo()))
//                break;
//        }
//        return false;
//    }
}
