
package team5.client.searches;

import java.util.regex.*;
import team5.client.user.User;
import java.util.*;
import team5.client.actions.WorkUser;
import team5.client.exceptions.UserNotFoundException;

/**
 * Search of users on a regular expression or a substring
 * @author Dmitry
 */
//Возможно есть смысл реализовать как Singleton
public class UserSearch implements Search {

    //private  ArrayList<User>  resultOfSearch;
    /**
     * Search method for users on a regular expression
     *
     * @author Dmitry
     * @param request 
     * @throws UserNotFoundException
     */
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

    /**
     * Search method for users on a substring
     *
     * @author Dmitry
     * @param request 
     * @throws UserNotFoundException
     */
    @Override
    public List substringSearch(String request)
            throws UserNotFoundException {

        ArrayList<User> users = WorkUser.getWork().getArrOfUsers();
        ArrayList<User> resultOfSearch = new ArrayList<User>();

        for (User u : users) {
            if (SearchServices.isStringIncludeSubstring(u.getAddress().getCity().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.isStringIncludeSubstring(u.getAddress().getCountry().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.isStringIncludeSubstring(u.getPrivateInformation().getName().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.isStringIncludeSubstring(u.getPrivateInformation().getSurname().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.isStringIncludeSubstring(u.getPrivateInformation().getbDay().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.isStringIncludeSubstring(u.getServiceInfo().getDateOfRegistration().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.isStringIncludeSubstring(u.getServiceInfo().getEmail().toString(), request)) {
                resultOfSearch.add(u);
                continue;
            }
            if (SearchServices.isStringIncludeSubstring(u.getServiceInfo().getLogin().toString(), request)) {
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
