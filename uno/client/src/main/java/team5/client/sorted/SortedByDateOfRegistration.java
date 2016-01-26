/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.sorted;

import java.time.LocalDate;
import java.util.Comparator;
import team5.client.user.User;

/**
 *
 * @author chanta
 */
public class SortedByDateOfRegistration implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        LocalDate str1 = o1.getServiceInfo().getDateOfRegistration();
        LocalDate str2 = o2.getServiceInfo().getDateOfRegistration();

        return str1.compareTo(str2);
    }
}
