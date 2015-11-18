/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.searches;

import java.util.regex.*;
import team5.desktop.user.User;
import java.util.*;
import team5.desktop.actions.WorkUser;
import team5.desktop.exceptions.UserNotFoundException;

/**
 *
 * @author Dmitry
 */
public class UserSearch {
    
    //private  ArrayList<User>  resultOfSearch;
   
    
    public static List Search(String request)
    throws UserNotFoundException{ 
        ArrayList<User>   users=WorkUser.getWork().getArrOfUsers();
        ArrayList<User>  resultOfSearch=new ArrayList<User>();
        Pattern p = Pattern.compile(request); 
        for(User u : users){            
            Matcher m = p.matcher(u.getAddress().getCity().toString());
            if(m.matches()){
                resultOfSearch.add(u);
                        continue;
            }
             m = p.matcher(u.getAddress().getCountry().toString());
            if(m.matches()){
                resultOfSearch.add(u);
                        continue;
            }
             m = p.matcher(u.getPrivateInformation().getName().toString());
            if(m.matches()){
                resultOfSearch.add(u);
                        continue;
            }
             m = p.matcher(u.getPrivateInformation().getSurname().toString());
            if(m.matches()){
                resultOfSearch.add(u);
                        continue;
            }
             m = p.matcher(u.getPrivateInformation().getbDay().toString());
            if(m.matches()){
                resultOfSearch.add(u);
                        continue;
            }
             m = p.matcher(u.getServiceInfo().getDateOfRegistration().toString());
            if(m.matches()){
                resultOfSearch.add(u);
                        continue;
            }
             m = p.matcher(u.getServiceInfo().getEmail().toString());
            if(m.matches()){
                resultOfSearch.add(u);
                        continue;
            }
             m = p.matcher(u.getServiceInfo().getLogin().toString());
            if(m.matches()){
                resultOfSearch.add(u);
                        continue;
            }
             m = p.matcher(u.getServiceInfo().getPassword().toString());
            if(m.matches()){
                resultOfSearch.add(u);
                        continue;
            }
            
        }
        if(resultOfSearch.isEmpty()){
            throw new UserNotFoundException();
        }
         
        return resultOfSearch;  
} 
}
