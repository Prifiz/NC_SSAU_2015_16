/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.actions;

import java.io.Serializable;
import java.util.ArrayList;
import team5.datamodel.card.Card;
import team5.datamodel.card.NumericCard;
import team5.datamodel.exceptions.CardNotFoundException;
import team5.datamodel.exceptions.NotFoundException;
import team5.datamodel.searches.CardSearch;
import team5.datamodel.searches.Search;

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

    public Card addCard(Integer icon, String color) {
        Card card = new NumericCard(icon, color);
        arrCards.add(card);
        return card;
    }

    public Card getCardOfIndex(int index) {
        return arrCards.get(index);
    }

    public int getOfCountCards() {
        return arrCards.size();
    }

    public ArrayList<Card> getArrOfCards() {
        return arrCards;
    }

    public void addWorkCard(WorkCard card) {
        for (int i = 0; i < card.getOfCountCards(); i++) {
            arrCards.add(card.getCardOfIndex(i));
        }
    }

    public void deleteCard(Integer icon, String color) throws CardNotFoundException {
        Search search = new CardSearch();
//        Card card = this.search(icon, color);
        Card card;
//        if (card != null) {
        try {
            card=(Card)search.fieldSearch(color, "icon_color").get(0);
            arrCards.remove(card);
        } catch (NotFoundException ex) {
            throw new CardNotFoundException("Delete Card: ");
        }
    }
}

////временая мера для того, чтобы написать метод удаления
//public Card search(int icon, String color) throws CardNotFoundException {
//        int i = 0;
//        if (arrCards.isEmpty()) {
//            throw new CardNotFoundException("Array of cards is empty");
//        }
//        if ((color == null)) {
//            throw new CardNotFoundException("Uncorrect color");
//        }
//        while ((i < arrCards.size()) && (!arrCards.get(i).getColor().equals(color)) && (arrCards.get(i).getIcon() != icon)) {
//            i++;
//        }
//        if (i < arrCards.size()) {
//            return arrCards.get(i);
//        }
//        throw new CardNotFoundException("Card not found");
//
//    }


