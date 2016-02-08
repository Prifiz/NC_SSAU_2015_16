/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import team5.server.actions.TableController;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import org.junit.Test;
import static org.junit.Assert.*;
import team5.datamodel.card.Card;
import team5.datamodel.card.NumericCard;

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
        Card card = new NumericCard(9, "green");
        TableController instance = new TableController();
        instance.setLastCard(new NumericCard(9, "green"));
        boolean expResult = true;
        boolean result = instance.isRightCard(card);
        assertEquals(expResult, result);
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

    /**
     * Test of setNewPack method, of class TableController.
     */
    @Test
    public void testSetNewPack() throws FileNotFoundException {
        System.out.println("setNewPack");
        Reader in = new FileReader("Cards.txt");
        TableController instance = new TableController();
        instance.loadNewPack();
        Card expResult = new NumericCard(9, "green");
        Card result = instance.getPack().get(instance.getPack().size() - 1);
        assertEquals(expResult, result);
    }

}
