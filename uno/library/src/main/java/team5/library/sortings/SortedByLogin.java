/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.library.sortings;

import java.util.Comparator;
import team5.library.user.User;

/**
 *
 * @author chanta
 */
public class SortedByLogin implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        String str1 = o1.getServiceInfo().getLogin();
        String str2 = o2.getServiceInfo().getLogin();

        return str1.compareTo(str2);
    }
}