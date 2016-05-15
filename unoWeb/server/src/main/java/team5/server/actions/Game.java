/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import team5.datamodel.card.Card;

/**
 *
 * @author андрей
 */
public class Game {
    
    private int gamerNumber;
    private String gamerLogin;
    private RoomController room;
    private TableController table;
    
    
    
    public Game(RoomController room, TableController table){
        this.room = room;
        this.table = table;
    }
    
    public String gameProcess(String login, Card card){
        if(gamerLogin.equals(login)){
            if(room.getGamer(login).searchCardInHand(card)){
                if(table.isRightCard(card)){
                    table.setLastCard(card);
                    if(gamerNumber+1<room.countGamers())
                    {
                        gamerNumber++;
                    }
                    else{
                        gamerNumber=0;
                    }
                    gamerLogin = room.getGamer(gamerNumber).getGamerLogin();
                    return "Success";
                }else{
                    return "Wrong card";
                }
            }
            else{
                return "Gamer use chit code";
            }
        }else{
            return "Is not your turn";
        }
    }
    
    public Card getLastCard(){
        return table.getLastCard();
    }
}