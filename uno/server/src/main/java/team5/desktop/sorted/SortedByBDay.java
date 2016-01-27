/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.sorted;

import java.time.LocalDate;
import java.util.Comparator;
import team5.desktop.user.User;


/**
 *
 * @author chanta
 */
public class SortedByBDay implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        LocalDate str1 = o1.getPrivateInformation().getbDay();
        LocalDate str2 = o2.getPrivateInformation().getbDay();

        return str1.compareTo(str2);
    }
}