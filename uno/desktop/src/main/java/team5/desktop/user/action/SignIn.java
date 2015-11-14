/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user.action;


import java.util.ArrayList;
import team5.desktop.user.User;

/**
 *
 * @author андрей
 */
public class SignIn {
    
    
    public boolean sign(String login, char[]password)
    {
        WorkUser work = WorkUser.getWork();
        User user = work.search(login);
        String s=""; 
        for (int i = 0; i <password.length ; i++) { 
            s+=password[i]; 
        }
        if(user.getServiceInfo().getPassword().compareTo(s)==0)
        {
            //открытие фрейма с комнатами
            return true;
        }
        else
            return false;
        
    }
}

