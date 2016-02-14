/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import java.time.LocalDate;
import team5.datamodel.actions.WorkUser;
import team5.datamodel.exceptions.*;

/**
 *
 * @author chanta
 */
public class Registration {

    public Boolean registrationUser(String name, String surname, String country, String city, String login, String password, String email, LocalDate bDay)
            throws UserExistException {
//        if(checkOfExistenceOfUser(user))
//            System.out.println("This user has already been created");
//        
        // WorkUser workUser= new WorkUser();
        try {
            WorkUser workUser = WorkUser.getWork();
            // apache commons-lang
            // StringUtils.isBlank(String string)
            if (("".equals(name)) || ("".equals(surname)) || ("".equals(login)) || ("".equals(password)) || ("".equals(email))) {
                
                return false;
            } else {
                workUser.addUser(name, surname, country, city, login, password, email, bDay);
                return true;
            }
        } catch (UserExistException e) {
            throw new UserExistException("Registration " + e.getMessage());
            //return false;
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
