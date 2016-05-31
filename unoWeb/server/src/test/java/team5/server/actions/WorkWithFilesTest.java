/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import org.junit.Test;
import team5.datamodel.card.Card;
import team5.datamodel.transmissions.FileHandler;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

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
//        Card expResult = new Card(9, "green");//FIXME
        in.close();
        //assertEquals(expResult, result);//FIXME
        //fail("The test case is a prototype.");

    }

}
