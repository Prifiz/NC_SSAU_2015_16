/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import org.junit.Test;
import team5.datamodel.card.Card;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author андрей
 */
public class TableControllerTest {

    public TableControllerTest() {
    }

    /**
     * Test of isRightCard method, of class TableController.
     */
    @Test
    public void testIsRightCard() {
        System.out.println("isRightCard");
    }

    /**
     * Test of getCardFromPack method, of class TableController.
     */
    @Test
    public void testGetCardFromPack() {
        System.out.println("getCardFromPack");
        TableController instance = new TableController();
        int expResult = instance.getPack().size() - 1;
        Card card = instance.getCardFromPack();
        int result = instance.getPack().size();
        assertEquals(expResult, result);
    }
}
