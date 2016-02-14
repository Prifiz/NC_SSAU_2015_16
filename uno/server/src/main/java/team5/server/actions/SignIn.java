/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import team5.datamodel.user.User;
import team5.datamodel.exceptions.*;
import team5.datamodel.searches.Search;
import team5.datamodel.searches.UserSearch;

/**
 *
 * @author андрей
 */
public class SignIn {

    /**
     *
     * @param login
     * @param password
     * @return
     */
    
    //На данный момент использований не обнаружено
    public boolean sign(String login, String password) {
        try {
            Search search=new UserSearch();
            User user = (User)search.fieldSearch(login, "login").get(0);
            String s = "";
            /*for (int i = 0; i < password.length; i++) {
                s += password[i];
            }*/
            if (user.getServiceInfo().getPassword().equals(password)) {
                //открытие фрейма с комнатами
                return true;
            } else {
                return false;
            }
        } catch (NotFoundException e) {
            return false;
            //Надо написать обработчик некорректного логина, пароля!!!!!
        }

    }
}
