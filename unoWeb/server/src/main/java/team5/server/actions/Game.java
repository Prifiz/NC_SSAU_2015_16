/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import team5.datamodel.card.Card;
import team5.datamodel.card.Cards;
import team5.server.net.server.ServerConstants;

/**
 *
 * @author андрей
 */
public class Game {
    
    private boolean[] firstDistrib;
    
    private int gamerNumber;
    private String gamerLogin;
    private RoomController room;
    private TableController table;
    
    
    
    public Game(RoomController room){
        firstDistrib = new boolean [ServerConstants.MAX_NUMBER_OF_PLAYERS];
        for (int i = 0; i < firstDistrib.length; i++) {
            firstDistrib[i] = false;
        }
        this.room = room;
        this.table = this.room.getTableController();
        table.loadNewPack();
    }
    
    public void distribCard(String login){
        for (int i = 0; i < ServerConstants.START_NUMBER_OF_CARDS; i++) {
           room.getGamer(login).addCardToHand(table.getCardFromPack());
        }
        firstDistrib[room.getGamerNumber(room.getGamer(login))] = true;
        
    }
    public boolean takeCard(String login){
        boolean b = room.getGamer(login).isCanTakeCard();
        if(room.getGamer(login).isCanTakeCard()){
            room.getGamer(login).addCardToHand(table.getCardFromPack());
            room.getGamer(login).setCanTakeCard(false);
        }
        return b;
    }
    
    public String gameProcess(String login, Integer cardId){
        Card card = Cards.getCardById(cardId);
        if(gamerLogin.equals(login)){
            room.getGamer(login).setCanTakeCard(true);
            if(room.getGamer(login).searchCardInHand(card)){
                if(table.isRightCard(card)){
                    table.setLastCard(card);
                    room.getGamer(login).removeCardInHand(card);
                    if(gamerNumber+1<room.countGamers())
                    {
                        gamerNumber++;
                    }
                    else{
                        gamerNumber=0;
                    }
                    gamerLogin = room.getGamer(gamerNumber).getGamerLogin();
                    room.getGamer(login).setCanTakeCard(false);
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
    public int getGamerNumber() {
        return gamerNumber;
    }

    public void setGamerNumber(int gamerNumber) {
        this.gamerNumber = gamerNumber;
    }

    public String getGamerLogin() {
        return gamerLogin;
    }

    public void setGamerLogin(String gamerLogin) {
        this.gamerLogin = gamerLogin;
    }

    public RoomController getRoom() {
        return room;
    }

    public void setRoom(RoomController room) {
        this.room = room;
    }

    public TableController getTable() {
        return table;
    }

    public void setTable(TableController table) {
        this.table = table;
    }
    
    public boolean getFirstDistrib(int i) {
        return firstDistrib[i];
    }

    public void setFirstDistrib(boolean firstDistrib, int i) {
        this.firstDistrib[i] = firstDistrib;
    }
}
