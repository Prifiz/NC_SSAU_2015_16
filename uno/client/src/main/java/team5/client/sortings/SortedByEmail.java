/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.sortings;

import java.util.Comparator;
import team5.client.user.User;

/**
 *
 * @author chanta
 */
public class SortedByEmail implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        String str1 = o1.getServiceInfo().getEmail();
        String str2 = o2.getServiceInfo().getEmail();

        return str1.compareTo(str2);
    }
}
