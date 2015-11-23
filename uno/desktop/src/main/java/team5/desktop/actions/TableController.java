/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.actions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import team5.desktop.card.Card;

/**
 *
 * @author андрей
 */
public class TableController {
    
    private ArrayList<Card> pack;
    private Card lastcard;
    private static final TableController tablecontroller = new TableController();
    private int counter_for_random;
    
    private TableController()
    {
        setNewPack();
        counter_for_random = 76;
    }
    
    public static TableController getTableController()
    {
        return tablecontroller;
    }
    
    public ArrayList<Card> getPack() {
        return pack;
    }
    public Card getLastCard()
    {
        return lastcard;
    }
    public void setLastCard(Card card)
    {
        lastcard = card;
    }
    public boolean isRightCard(Card card)
    {
        if(lastcard.equals(card))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Card getCardFromPack()
    {
        Random random = new Random();
        int i = random.nextInt(counter_for_random)+1;
        Card card = pack.get(i);
        pack.remove(i);
        counter_for_random--;
        return card;
    }
    
    public void setNewPack()
    {
        FileReader fr = null;
        try {
            fr = new FileReader("Cards.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pack = WorkWithFiles.readCards(fr);
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
