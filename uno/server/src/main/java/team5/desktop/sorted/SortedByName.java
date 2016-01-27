/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.sorted;

import java.util.Comparator;
import team5.desktop.user.User;

/**
 *
 * @author chanta
 */
public class SortedByName implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        String str1 = o1.getPrivateInformation().getName();
        String str2 = o2.getPrivateInformation().getName();

        return str1.compareTo(str2);
    }

}