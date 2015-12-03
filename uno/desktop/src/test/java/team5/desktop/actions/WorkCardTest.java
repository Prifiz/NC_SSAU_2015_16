/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.actions;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import team5.desktop.card.*;

/**
 *
 * @author chanta
 */
public class WorkCardTest {

    public WorkCardTest() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of addCard method, of class WorkCard.
     */
    @Test
    public void testAddCard() {
        System.out.println("testAddCard: ");
        WorkCard workCard = new WorkCard();
        Card expected = new NumericCard(1, "red");
        Card result = workCard.addCard(1, "red");
        assertEquals(expected, result);
    }

    /**
     * Test of addCard method, of class WorkCard.
     */
    @Test
    public void testAddCard_1() {
        System.out.println("testAddCard_1: ");
        WorkCard workCard = new WorkCard();
        Card expected = null;
        Card result = workCard.addCard(1, "red");
        assertEquals(expected, result);
        fail("Not Equals!");
    }

}
