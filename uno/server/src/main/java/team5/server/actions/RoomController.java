/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import java.util.ArrayList;

/**
 *
 * @author андрей
 */
public class RoomController {

    private TableController table;
    private ArrayList<GamerController> gamers;
    private boolean playing = false;
    private boolean finish = false;
    private String command;

    public RoomController() {
        table = new TableController();
        gamers = new ArrayList();
    }


    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public GamerController getGamer(String login) {
        GamerController gamer = null;
        for (int i = 0; i < gamers.size(); i++) {
            if (gamers.get(i).getGamerLogin().equals(login)) {
                gamer = gamers.get(i);
            }
        }
        return gamer;
    }

    public GamerController getGamer(int i) {
        return gamers.get(i);
    }

    public int getGamerNumber(GamerController gamer) {
        int index = 0;
        for (int i = 0; i < gamers.size(); i++) {
            if (gamers.get(i).getGamerLogin().equals(gamer.getGamerLogin())) {
                index = i;
            }
        }
        return index;
    }

    public ArrayList<GamerController> getGamers() {
        return gamers;
    }

    public int countGamers() {
        return gamers.size();
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

    public TableController getTableController() {
        return table;
    }

    public void cleanRoom() {
        for (int i = 0; i < gamers.size(); i++) {
            gamers.remove(i);
        }
        playing = false;
        table.cleanTable();
    }

}
