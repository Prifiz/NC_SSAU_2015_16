/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import team5.datamodel.user.User;
import team5.datamodel.exceptions.*;
import team5.datamodel.searches.FieldRequest;
import team5.datamodel.searches.Search;
import team5.datamodel.searches.UserSearch;
import team5.datalayer.database.Searcher;

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
            Searcher searcher = new Searcher();
            //Search search=new UserSearch();
            //User user = (User)search.searchByField(login, FieldRequest.LOGIN).get(0);
            String dbPassword = searcher.searchPassword(login);
            /*for (int i = 0; i < password.length; i++) {
                s += password[i];
            }*/
            boolean b = false;
            if (!"Пусто".equals(dbPassword)) {
                if (dbPassword.equals(password)) {
                    //открытие фрейма с комнатами
                    b =  true;
                } else {
                    b =  false;
                }
            }
            return b;
        /*catch (NotFoundException e) {
            return false;
            //Надо написать обработчик некорректного логина, пароля!!!!!
        }*/
    }
}
