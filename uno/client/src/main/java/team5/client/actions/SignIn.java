/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.actions;

import team5.client.user.User;
import team5.client.exceptions.*;

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
    public boolean sign(String login, String password) {
        try {
            WorkUser work = WorkUser.getWork();
            User user = work.search(login);
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
        } catch (UserNotFoundException e) {
            return false;
            //Надо написать обработчик некорректного логина, пароля!!!!!
        }

    }
}
