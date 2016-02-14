package team5.datamodel.searches;

import java.util.regex.*;
import team5.datamodel.user.User;
import java.util.*;
import team5.datamodel.actions.WorkUser;
import team5.datamodel.exceptions.UserNotFoundException;

/**
 * Search of users on a regular expression or a substring
 *
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
    public List searchByRegexp(String request)
            throws UserNotFoundException {
        ArrayList<User> users = WorkUser.getWork().getArrOfUsers();
        ArrayList<User> resultOfSearch = new ArrayList<User>();
        Pattern p = Pattern.compile(request);
        Matcher m;
        for (User user : users) {
            m = p.matcher(user.getAddress().getCity().toString());
            if (m.matches()) {
                resultOfSearch.add(user);
                continue;
            }
            m = p.matcher(user.getAddress().getCountry().toString());
            if (m.matches()) {
                resultOfSearch.add(user);
                continue;
            }
            m = p.matcher(user.getPrivateInformation().getName().toString());
            if (m.matches()) {
                resultOfSearch.add(user);
                continue;
            }
            m = p.matcher(user.getPrivateInformation().getSurname().toString());
            if (m.matches()) {
                resultOfSearch.add(user);
                continue;
            }
            m = p.matcher(user.getPrivateInformation().getbDay().toString());
            if (m.matches()) {
                resultOfSearch.add(user);
                continue;
            }
            m = p.matcher(user.getServiceInfo().getDateOfRegistration().toString());
            if (m.matches()) {
                resultOfSearch.add(user);
                continue;
            }
            m = p.matcher(user.getServiceInfo().getEmail().toString());
            if (m.matches()) {
                resultOfSearch.add(user);
                continue;
            }
            m = p.matcher(user.getServiceInfo().getLogin().toString());
            if (m.matches()) {
                resultOfSearch.add(user);
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
    public List searchBySubstring(String request)
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

    /**
     * Search method for cards on a fields
     *
     * @author Dmitry
     * @param request
     * @param field takes values: "city", "country", "name", "surname", "bDay",
     * "dateOfRegistration", "email", "login"
     * @throws CardNotFoundException
     */
    @Override
    public List searchByField(String request, String field)
            throws UserNotFoundException {

        ArrayList<User> users = WorkUser.getWork().getArrOfUsers();
        ArrayList<User> resultOfSearch = new ArrayList<User>();

        if ("city".equals(field) || "City".equals(field)) {
            for (User u : users) {
                if (SearchServices.isStringIncludeSubstring(u.getAddress().getCity().toString(), request)) {
                    resultOfSearch.add(u);
                }
            }
        }

        if ("country".equals(field) || "Country".equals(field)) {
            for (User u : users) {
                if (SearchServices.isStringIncludeSubstring(u.getAddress().getCountry().toString(), request)) {
                    resultOfSearch.add(u);
                }
            }
        }

        if ("name".equals(field) || "Name".equals(field)) {
            for (User u : users) {
                if (SearchServices.isStringIncludeSubstring(u.getPrivateInformation().getName().toString(), request)) {
                    resultOfSearch.add(u);
                }
            }
        }

        if ("surname".equals(field) || "Surname".equals(field)) {
            for (User u : users) {
                if (SearchServices.isStringIncludeSubstring(u.getPrivateInformation().getSurname().toString(), request)) {
                    resultOfSearch.add(u);
                }
            }
        }

        if ("bDay".equals(field) || "BDay".equals(field)) {
            for (User u : users) {
                if (SearchServices.isStringIncludeSubstring(u.getPrivateInformation().getbDay().toString(), request)) {
                    resultOfSearch.add(u);
                }
            }
        }

        if ("dateOfRegistration".equals(field) || "DateOfRegistration".equals(field)) {
            for (User u : users) {
                if (SearchServices.isStringIncludeSubstring(u.getServiceInfo().getDateOfRegistration().toString(), request)) {
                    resultOfSearch.add(u);
                }
            }
        }

        if ("email".equals(field) || "Email".equals(field)) {
            for (User u : users) {
                if (SearchServices.isStringIncludeSubstring(u.getServiceInfo().getEmail().toString(), request)) {
                    resultOfSearch.add(u);
                }
            }
        }

        if ("login".equals(field) || "Login".equals(field)) {
            for (User u : users) {
                if (SearchServices.isStringIncludeSubstring(u.getServiceInfo().getLogin().toString(), request)) {
                    resultOfSearch.add(u);
                }
            }
        }

        if (resultOfSearch.isEmpty()) {
            throw new UserNotFoundException();
        }

        return resultOfSearch;

    }

}
