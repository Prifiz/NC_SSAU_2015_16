/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.card;

import team5.datamodel.transmissions.FileHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author андрей
 */
public class Cards {
     static private ArrayList<Card> cards ;

    public Cards(){
         try {
             this.cards = FileHandler.readCards(new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Cards.txt"))));
         } catch (IOException ex) {
             Logger.getLogger(Cards.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

   static public void addCard(Card card){
       cards.add(card);
   }
   
   static public Card getCardById(Integer id){
       Card card2= null;
       for(Card card:cards){
           if(card.getCardId().equals(id)){
               card2=card;
           }
       }
       return card2;
   }
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setColors(ArrayList<Card> cards) {
        this.cards = cards;
    }
    
}
