/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import java.util.ArrayList;
import java.util.Objects;
import team5.datamodel.card.Card;
import team5.datamodel.exceptions.CardNotFoundException;

/**
 *
 * @author андрей
 */
public class GamerController {
    private ArrayList<Card> handscards;
    private String login;
    private String act;
    private int readCount;
    private boolean canTakeCard = false;

    public boolean isCanTakeCard() {
        return canTakeCard;
    }

    public void setCanTakeCard(boolean canTakeCard) {
        this.canTakeCard = canTakeCard;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

   
    public GamerController(String login)
    {
        handscards  = new ArrayList();
        this.login = login;
    }
     public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }
    public ArrayList<Card> getHandscards() {
        return handscards;
    }

    public void setHandscards(ArrayList<Card> handscards) {
        this.handscards = handscards;
    }
    public void addCardToHand(Card card)
    {
        handscards.add(card);
    }
    public void removeCardInHand(Card card){
        for(Card card2: handscards){
            if(card2.equals(card)){
                handscards.remove(card2);
            }
        }
    }
    
    public void pullCardFromHand(Card card) throws CardNotFoundException
    {
        if(!handscards.isEmpty())
        {
            for (int i = 0; i < handscards.size(); i++) {
                if(Objects.equals(card.getCardId(), handscards.get(i).getCardId()))
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
    
    public Card searchCardInHand(int cardId)
    {
       // String[] str = s.split(" ");
        Card b = null;
        for (Card handscard : handscards) {
            if (handscard.getCardId()==cardId)//((handscard.getColorId()==Integer.getInteger(str[0])) && (handscard.getIconId() == Integer.parseInt(str[1]))) {
                b = handscard;
        }
        return b;
    }
    
}
