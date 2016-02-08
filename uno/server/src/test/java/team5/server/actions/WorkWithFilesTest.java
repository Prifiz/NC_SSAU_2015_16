/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import team5.datamodel.transmissions.FileHandler;
import team5.datamodel.card.Card;
import team5.datamodel.card.NumericCard;

/**
 *
 * @author андрей
 */
public class WorkWithFilesTest {

    public WorkWithFilesTest() {
    }

    /**
     * Test of readCards method, of class WorkWithFiles.
     */
    @Test
    public void testReadCards() throws Exception {
        System.out.println("readCards");
        Reader in = new FileReader("Cards.txt");
        ArrayList<Card> pack = FileHandler.readCards(in);;
        Card result = pack.get(pack.size() - 1);
        Card expResult = new NumericCard(9, "green");
        in.close();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");

    }

}
