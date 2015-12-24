/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.actions;

import java.util.ArrayList;

/**
 *
 * @author андрей
 */
public class RoomController {

    private TableController table;
    private ArrayList<GamerController> gamers;

    public ArrayList<GamerController> getGamers() {
        return gamers;
    }

    public RoomController() {
        table = new TableController();
        gamers = new ArrayList();
    }

    public void addGamer(GamerController gamer) {
        gamers.add(gamer);
    }

    public void removeGamer(GamerController gamer) {
        for (int i = 0; i < gamers.size(); i++) {
            if (gamers.get(i).getGamerLogin().equals(gamer.getGamerLogin())) {
                gamers.remove(i);
            }
        }
    }
    public TableController getTableController()
    {
        return table;
    }

}
