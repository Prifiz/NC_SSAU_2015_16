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
    private int counter_for_random;

    public TableController() {
        loadNewPack();
        counter_for_random = 75;
    }

    public ArrayList<Card> getPack() {
        return pack;
    }

    public Card getLastCard() {
        return lastcard;
    }

    public void setLastCard(Card card) {
        lastcard = card;
    }

    public boolean isRightCard(Card card) {
        if(lastcard!=null)
        {
        if ((lastcard.getColor().equals(card.getColor()))||(lastcard.getIcon()==card.getIcon())) {
            lastcard = card;
            return true;
        } else {
            return false;
        }
        }
        else
        {
            lastcard = card;
            return true;
        }
    }

    public Card getCardFromPack() {
        Random random = new Random();
        int i = random.nextInt(counter_for_random) + 1;
        Card card = pack.get(i);
        pack.remove(i);
        counter_for_random--;
        return card;
    }

    public void loadNewPack() {
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

    public void setNewPack(ArrayList<Card> l1, ArrayList<Card> l2)
    {
        loadNewPack();
        for (Card l11 : l1) {
            pack.remove(l11);
        }
        for (Card l22:l2)
        {
            pack.remove(l22);
        }
        pack.remove(lastcard);
    }
            
}
