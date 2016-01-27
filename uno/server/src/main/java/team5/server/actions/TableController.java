/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.apache.log4j.Logger;
import team5.library.actions.WorkWithFiles;
import team5.library.card.Card;

/**
 *
 * @author андрей
 */
public class TableController {

    private boolean cardDeal;
    private Logger log = Logger.getLogger(TableController.class);
    private ArrayList<Card> pack;
    private Card lastcard;
    private int counterForRandom;

    public TableController() {
        loadNewPack();
        counterForRandom = 75;
    }

    public boolean isCardDeal() {
        return cardDeal;
    }

    public void setCardDeal(boolean isCardDeal) {
        this.cardDeal = isCardDeal;
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

    /**
     * This method return true, if card is equivalent to the last card in tabel.
     * Else return false.
     *
     * @param card
     * @return true/false
     */
    public boolean isRightCard(Card card) {
        if (lastcard != null) {
            if ((lastcard.getColor().equals(card.getColor())) || (lastcard.getIcon() == card.getIcon())) {
                lastcard = card;
                return true;
            } else {
                return false;
            }
        } else {
            lastcard = card;
            return true;
        }
    }

    public Card getCardFromPack() {
        Random random = new Random();
        int i = random.nextInt(counterForRandom) + 1;
        Card card = pack.get(i);
        pack.remove(i);
        counterForRandom--;
        return card;
    }

    public void loadNewPack() {
        FileReader fr = null;
        try {
            fr = new FileReader("Cards.txt");
        } catch (FileNotFoundException ex) {
            log.debug(ex.getMessage());
        }
        try {
            pack = WorkWithFiles.readCards(fr);
        } catch (IOException ex) {
            log.debug(ex.getMessage());
        }
    }

    public void setNewPack(ArrayList<Card> cards1, ArrayList<Card> cards2) {
        loadNewPack();
        for (Card card : cards1) {
            pack.remove(card);
        }
        for (Card card : cards2) {
            pack.remove(card);
        }
        pack.remove(lastcard);
    }

}
