/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import java.util.logging.Level;
import org.apache.log4j.Logger;
import team5.datamodel.card.Card;
import team5.datamodel.card.Cards;
import team5.datamodel.exceptions.CardNotFoundException;
import team5.server.net.server.ServerConstants;
import team5.server.net.server.ServerThread;

/**
 *
 * @author андрей
 */
public class Game {

    private boolean[] firstDistrib;
    private Logger logger = Logger.getLogger(Game.class);
    private int gamerNumber;
    private String gamerLogin;
    private RoomController room;
    private TableController table;
    private String winner;

    public Game(RoomController room) {

        firstDistrib = new boolean[ServerConstants.MAX_NUMBER_OF_PLAYERS];
        winner = "no";
        for (int i = 0; i < firstDistrib.length; i++) {
            firstDistrib[i] = false;
        }
        this.room = room;
        this.table = this.room.getTableController();
        gamerLogin = room.getGamer(0).getGamerLogin();
        room.getGamer(gamerLogin).setCanTakeCard(true);
        table.loadNewPack();
    }

    public void distribCard(String login) {
        for (int i = 0; i < ServerConstants.START_NUMBER_OF_CARDS; i++) {
            room.getGamer(login).addCardToHand(table.getCardFromPack());
        }
        firstDistrib[room.getGamerNumber(room.getGamer(login))] = true;

    }

    public void takeCard(String login) {
        if (gamerLogin.equals(login)) {
            room.getGamer(login).addCardToHand(table.getCardFromPack());
        }
    }

    public String gameProcess(String login, Integer cardId) {
        Card card = room.getGamer(login).searchCardInHand(cardId);
        if (gamerLogin.equals(login)) {
            if (card != null) {
                if (table.isRightCard(card)) {
                    table.setLastCard(card);
                    try {
                        room.getGamer(login).pullCardFromHand(card);
                    } catch (CardNotFoundException ex) {
                        logger.debug(ex.getMessage());
                    }
                    if (room.getGamer(login).getHandscards().isEmpty()) {
                        winner = login;
                        room.cleanRoom();
                    }
                    if (gamerNumber + 1 < room.countGamers()) {
                        gamerNumber++;
                    } else {
                        gamerNumber = 0;
                    }
                    gamerLogin = room.getGamer(gamerNumber).getGamerLogin();
                    return "Success";
                } else {
                    return "Wrong card";
                }
            } else {
                return "Gamer use chit code";
            }
        } else {
            return "Is not your turn";
        }
    }

    public Card getLastCard() {
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
