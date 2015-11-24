/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.actions;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import team5.desktop.user.User;

/**
 *
 * @author андрей
 */
public class WorkUserTest {

    public WorkUserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getWork method, of class WorkUser.
     */
    @org.junit.Test
    public void testGetWork() {
        System.out.println("getWork");
        WorkUser expResult = null;
        WorkUser result = WorkUser.getWork();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArrOfUsers method, of class WorkUser.
     */
    @org.junit.Test
    public void testGetArrOfUsers() {
        System.out.println("getArrOfUsers");
        WorkUser instance = new WorkUser();
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.getArrOfUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class WorkUser.
     */
    @org.junit.Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        String name = "";
        String surname = "";
        String country = "";
        String sity = "";
        String login = "";
        String password = "";
        String email = "";
        String bDay = "";
        WorkUser instance = new WorkUser();
        User expResult = null;
        User result = instance.addUser(name, surname, country, sity, login, password, email, bDay);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserOfIndex method, of class WorkUser.
     */
    @org.junit.Test
    public void testGetUserOfIndex() {
        System.out.println("getUserOfIndex");
        int index = 0;
        WorkUser instance = new WorkUser();
        User expResult = null;
        User result = instance.getUserOfIndex(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOfCountUser method, of class WorkUser.
     */
    @org.junit.Test
    public void testGetOfCountUser() {
        System.out.println("getOfCountUser");
        WorkUser instance = new WorkUser();
        int expResult = 0;
        int result = instance.getOfCountUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addWorkUser method, of class WorkUser.
     */
    @org.junit.Test
    public void testAddWorkUser() {
        System.out.println("addWorkUser");
        WorkUser wu = null;
        WorkUser instance = new WorkUser();
        instance.addWorkUser(wu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stringToLocalDate method, of class WorkUser.
     */
    @org.junit.Test
    public void testStringToLocalDate() {
        System.out.println("stringToLocalDate");
        String s = "";
        WorkUser instance = new WorkUser();
        LocalDate expResult = null;
        LocalDate result = instance.stringToLocalDate(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editUser method, of class WorkUser.
     */
    @org.junit.Test
    public void testEditUser() throws Exception {
        System.out.println("editUser");
        String name = "";
        String surname = "";
        String country = "";
        String sity = "";
        String login = "";
        String password = "";
        LocalDate calendar = null;
        String email = "";
        LocalDate bDay = null;
        WorkUser instance = new WorkUser();
        User expResult = null;
        User result = instance.editUser(name, surname, country, sity, login, password, calendar, email, bDay);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class WorkUser.
     */
    @org.junit.Test
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        String login = "";
        WorkUser instance = new WorkUser();
        instance.deleteUser(login);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class WorkUser.
     */
    @org.junit.Test
    public void testSearch() throws Exception {
        System.out.println("search");
        String login = "";
        WorkUser instance = new WorkUser();
        User expResult = null;
        User result = instance.search(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewUsers method, of class WorkUser.
     */
    @org.junit.Test
    public void testViewUsers() {
        System.out.println("viewUsers");
        WorkUser instance = new WorkUser();
        String expResult = "";
        String result = instance.viewUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
