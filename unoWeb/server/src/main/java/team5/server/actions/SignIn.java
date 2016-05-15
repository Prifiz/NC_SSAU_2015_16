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
import team5.datalayer.hibernate.dao.DaoFactory;
import team5.datalayer.hibernate.dao.PrivateInformationDao;
import team5.datamodel.user.PrivateInformation;

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
           
            String dbPassword = "1q";//searcher.searchPassword(login);
          
            boolean b = false;
            if (!"".equals(dbPassword)) {
                if (dbPassword.equals("1q")) {
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
