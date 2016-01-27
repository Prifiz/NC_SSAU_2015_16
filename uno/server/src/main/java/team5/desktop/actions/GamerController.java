<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.actions;

import java.util.ArrayList;
import team5.desktop.card.Card;
import team5.desktop.exceptions.CardNotFoundException;

/**
 *
 * @author андрей
 */
public class GamerController {
    private ArrayList<Card> handscards;
    private String login;
    private String act;
    private int readCount;

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
            if ((handscard.getColor().equals(str[0])) && (handscard.getIcon() == Integer.parseInt(str[1]))) {
                card =  handscard;
            }
        }
        return card;
    }
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.actions;

import java.util.ArrayList;
import team5.desktop.card.Card;
import team5.desktop.exceptions.CardNotFoundException;

/**
 *
 * @author андрей
 */
public class GamerController {
    private ArrayList<Card> handscards;
    private String login;
    private String act;
    private int readCount;

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
            if ((handscard.getColor().equals(str[0])) && (handscard.getIcon() == Integer.parseInt(str[1]))) {
                card =  handscard;
            }
        }
        return card;
    }
    
}
>>>>>>> a33f94e94560cfeb6beeed4f546b6747614b7cf4
