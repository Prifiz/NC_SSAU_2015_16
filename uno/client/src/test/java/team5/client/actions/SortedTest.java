/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.actions;

import org.junit.Before;
import org.junit.Test;
import team5.library.exceptions.UserExistException;
import team5.library.actions.WorkUser;

/**
 *
 * @author chanta
 */
public class SortedTest {
    
    @Before
    public void setUp() throws Exception {   
    }
    
    @Test
    public void testSortedByName() throws UserExistException {
        WorkUser workUser = WorkUser.getWork();
        workUser.addUser("bob4", "Clark", "Usa", "XX", "sfsdf", "1234", "email@gmail.com", "19.12.2012");
        workUser.addUser("abob4", "Clark1", "UsaZ", "X", "sf", "1234", "Aemail@gmail.com", "19.10.2012");
        workUser.addUser("cbob4", "Clark2", "Usad", "XXX", "sff", "1234", "Bemail@gmail.com", "13.12.2012");
        workUser.sortedUsers("name");
        System.out.println("testSortedByName");
        System.out.println(workUser.viewUsers());
    }

    @Test
    public void testSortedBySurname() throws UserExistException {
        WorkUser workUser = WorkUser.getWork();
        workUser.sortedUsers("surname");
        System.out.println("testSortedBySurname");
        System.out.println(workUser.viewUsers());
    }

    @Test
    public void testSortedByCountry() throws UserExistException {
        WorkUser workUser = WorkUser.getWork();
        workUser.sortedUsers("country");
        System.out.println("testSortedByCountry");
        System.out.println(workUser.viewUsers());
    }

    @Test
    public void testSortedByCity() throws UserExistException {
        WorkUser workUser = WorkUser.getWork();
        workUser.sortedUsers("city");
        System.out.println("testSortedByCity");
        System.out.println(workUser.viewUsers());
    }

    @Test
    public void testSortedByLogin() throws UserExistException {
        WorkUser workUser = WorkUser.getWork();
        workUser.sortedUsers("login");
        System.out.println("testSortedByLogin");
        System.out.println(workUser.viewUsers());
    }

    @Test
    public void testSortedByEmail() throws UserExistException {
        WorkUser workUser = WorkUser.getWork();
        workUser.sortedUsers("email");
        System.out.println("testSortedByEmail");
        System.out.println(workUser.viewUsers());
    }

    @Test
    public void testSortedByBday() throws UserExistException {
        WorkUser workUser = WorkUser.getWork();
        workUser.sortedUsers("bDay");
        System.out.println("testSortedByBday");
        System.out.println(workUser.viewUsers());
    }

    @Test
    public void testSortedByDateOfRegistration() throws UserExistException {
        WorkUser workUser = WorkUser.getWork();
        workUser.sortedUsers("dateOfRegistration");
        System.out.println("testSortedByDateOfRegistration");
        System.out.println(workUser.viewUsers());
    }
    
    
}
