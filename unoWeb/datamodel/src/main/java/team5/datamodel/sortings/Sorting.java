/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.sortings;

import java.time.LocalDate;
import java.util.Comparator;

import team5.datamodel.user.User;
import team5.datamodel.exceptions.NameComparatorException;
/**
 *
 * @author chanta
 */
public class Sorting {

    
    /**
     *
     * @param sortedBy the parameter that determines the sort order
     * @return
     */
    public Comparator sortedUsers(String sortedBy) {
        switch (sortedBy) {
            case "Name": {
                return new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        String str1 = user1.getPrivateInformation().getName();
                        String str2 = user2.getPrivateInformation().getName();
                        return str1.compareTo(str2);
                    }
                };
            }
            case "Surname": {
                return new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        String str1 = user1.getPrivateInformation().getSurname();
                        String str2 = user2.getPrivateInformation().getSurname();
                        return str1.compareTo(str2);
                    }
                };
            }
//            case "Country": {
//                return new Comparator<User>() {
//                    @Override
//                    public int compare(User user1, User user2) {
//                        String str1 = user1.getAddress().getCountry();
//                        String str2 = user2.getAddress().getCountry();
//                        return str1.compareTo(str2);
//                    }
//                };
//            }
            ///FIXME Модель поменял-поправки надо внести
            case "City": {
                return new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        String str1 = user1.getAddress().getLogin();
                        String str2 = user2.getAddress().getLogin();
                        return str1.compareTo(str2);
                    }
                };
            }
            case "Email": {
                return new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        String str1 = user1.getServiceInfo().getEmail();
                        String str2 = user2.getServiceInfo().getEmail();
                        return str1.compareTo(str2);
                    }
                };
            }
            case "Login": {
                return new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        String str1 = user1.getServiceInfo().getLogin();
                        String str2 = user2.getServiceInfo().getLogin();
                        return str1.compareTo(str2);
                    }
                };
            }
            case "BirthDay": {
                return new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        LocalDate date1 = user1.getPrivateInformation().getbDay();
                        LocalDate date2 = user2.getPrivateInformation().getbDay();
                        return date1.compareTo(date2);
                    }
                };
            }
            case "DateOfRegistration": {
                return new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        LocalDate date1 = user1.getServiceInfo().getDateOfRegistration();
                        LocalDate date2 = user2.getServiceInfo().getDateOfRegistration();
                        return date1.compareTo(date2);
                    }
                };
            }

        }
    return new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                try {
                    throw new NameComparatorException();
                } catch (NameComparatorException ex) {
                    System.err.println(ex);
                }
                return 0;
            }
        };
    }
}
