/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.actions;

import java.util.ArrayList;
import team5.datamodel.card.Card;
import team5.datamodel.exceptions.CardNotFoundException;

/**
 *
 * @author андрей
 */
public class GamerController {
    private ArrayList<Card> handscards;
    private String login;
    public GamerController(String login)
    {
        handscards  = new ArrayList();
        this.login = login;
    }
    
    public void addCardToHand(Card card)
    {
        handscards.add(card);
    }
    
    public void pullCardFromHand(Card card) throws CardNotFoundException
    {
        if(!handscards.isEmpty())
        {
            for (int i = 0; i < handscards.size(); i++) {
                if(card.equals(handscards.get(i)))
                {
                    handscards.remove(i);
                }
            }
        }
        else
        {
            throw new CardNotFoundException("Card not found");
        }
    }
    public boolean isHandEmpty()
    {
        if(handscards.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public String getGamerLogin()
    {
        return login;
    }
    
    public Card searchCardInHand(String s)
    {
        String[] str = s.split(" ");
        Card card = null;
        for (Card handscard : handscards) {
            if ((handscard.getColor().equals(str[0])) && (handscard.getIconId() == Integer.parseInt(str[1]))) {
                card =  handscard;
            }
        }
        return card;
    }
    
}
