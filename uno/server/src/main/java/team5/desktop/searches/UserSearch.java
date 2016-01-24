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
//Возможно есть смысл реализовать как Singleton
public class UserSearch implements Search {

    //private  ArrayList<User>  resultOfSearch;
    @Override
    public List regularSearch(String request)
            throws UserNotFoundException {
        ArrayList<User> users = WorkUser.getWork().getArrOfUsers();
        ArrayList<User> resultOfSearch = new ArrayList<User>();
        Pattern p = Pattern.compile(request);
        Matcher m;
        for (User u : users) {
            m = p.matcher(u.getAddress().getCity().toString());
            if (m.matches()) {
                resultOfSearch.add(u);
                continue;
            }
            m = p.matcher(u.getAddress().getCountry().toString());
            if (m.matches()) {
                resultOfSearch.add(u);
                continue;
            }
            m = p.matcher(u.getPrivateInformation().getName().toString());
            if (m.matches()) {
                resultOfSearch.add(u);
                continue;
            }
            m = p.matcher(u.getPrivateInformation().getSurname().toString());
            if (m.matches()) {
                resultOfSearch.add(u);
                continue;
            }
            m = p.matcher(u.getPrivateInformation().getbDay().toString());
            if (m.matches()) {
                resultOfSearch.add(u);
                continue;
            }
            m = p.matcher(u.getServiceInfo().getDateOfRegistration().toString());
            if (m.matches()) {
                resultOfSearch.add(u);
                continue;
            }
            m = p.matcher(u.getServiceInfo().getEmail().toString());
            if (m.matches()) {
                resultOfSearch.add(u);
                continue;
            }
            m = p.matcher(u.getServiceInfo().getLogin().toString());
            if (m.matches()) {
                resultOfSearch.add(u);
                continue;
            }
            //Был разговор, что по паролю поиск работать не должен. Пока закомментил
//            m = p.matcher(u.getServiceInfo().getPassword().toString());
//            if (m.matches()) {
//                resultOfSearch.add(u);
//                continue;
//            }

        }
        if (resultOfSearch.isEmpty()) {
            throw new UserNotFoundException();
        }

        return resultOfSearch;
    }

    @Override
    public List substringSearch(String request)
            throws UserNotFoundException {

        ArrayList<User> users = WorkUser.getWork().getArrOfUsers();
        ArrayList<User> resultOfSearch = new ArrayList<User>();

        for (User u : users) {
            if (SearchServices.substringSearchMethod(u.getAddress().getCity().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.substringSearchMethod(u.getAddress().getCountry().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.substringSearchMethod(u.getPrivateInformation().getName().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.substringSearchMethod(u.getPrivateInformation().getSurname().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.substringSearchMethod(u.getPrivateInformation().getbDay().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.substringSearchMethod(u.getServiceInfo().getDateOfRegistration().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.substringSearchMethod(u.getServiceInfo().getEmail().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.substringSearchMethod(u.getServiceInfo().getLogin().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
        }

        if (resultOfSearch.isEmpty()) {
            throw new UserNotFoundException();
        }

        return resultOfSearch;
    }

}
