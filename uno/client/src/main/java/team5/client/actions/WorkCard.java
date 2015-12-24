/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.actions;

import java.io.Serializable;
import java.util.ArrayList;
import team5.client.card.Card;
import team5.client.card.NumericCard;
import team5.client.exceptions.CardNotFoundException;

/**
 *
 * @author chanta
 */
public class WorkCard implements Serializable {

    private static final WorkCard work = new WorkCard();

    public static WorkCard getWork() {
        return work;
    }

    private final ArrayList<Card> arrCards;

    public WorkCard() {
        arrCards = new ArrayList();

    }

    public Card addCard(int icon, String color) {
        Card card = new NumericCard(icon, color);
        arrCards.add(card);
        return card;
    }

    public Card getCardOfIndex(int index) {
        return arrCards.get(index);
    }

    public int getOfCountCard() {
        return arrCards.size();
    }

    public void addWorkCard(WorkCard card) {
        for (int i = 0; i < card.getOfCountCard(); i++) {
            arrCards.add(card.getCardOfIndex(i));
        }
    }

    //вписать метод поиска карт!
    public void deleteUser(int icon, String color) throws CardNotFoundException {
        Card card = this.search(icon, color);
        if(card!=null)
        arrCards.remove(card);
    }

    //временая мера для того, чтобы написать метод удаления
    public Card search(int icon, String color) throws CardNotFoundException {
        int i = 0;
        if (arrCards.isEmpty()) {
            throw new CardNotFoundException("Array of users is empty");
        }
        if ((color == null)) {
            throw new CardNotFoundException("Uncorrect color");
        }
        while ((i < arrCards.size()) && (!arrCards.get(i).getColor().equals(color)) && (arrCards.get(i).getIcon() != icon)) {
            i++;
        }
        if (i < arrCards.size()) {
            return arrCards.get(i);
        }
        throw new CardNotFoundException("Card not found");

    }

}
