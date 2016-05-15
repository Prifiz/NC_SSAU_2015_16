/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import team5.datamodel.transmissions.Message;
import team5.server.net.server.ServerConstants;

/**
 *
 * @author андрей
 */
public class SelectRoom {

    private static SelectRoom selectRoom = new SelectRoom();

    public static SelectRoom getSelectRoom() {
        return selectRoom;
    }

    private int[] waitTime;
    private RoomController[] rooms;

    
    private Logger logger = Logger.getLogger(SelectRoom.class);

    public SelectRoom() {

    }

    public boolean select(int roomNumber, String login) {
        boolean b = false;
        if ((rooms[roomNumber].countGamers() < ServerConstants.MAX_NUMBER_OF_PLAYERS) && (rooms[roomNumber].isPlaying() == false)) {
            b = true;
        }
        return b;
    }

    public void waitGamers(int roomNumber, String login) {
        GamerController gamer = new GamerController(login);
        rooms[roomNumber].addGamer(gamer);//добавляем игрока в комнату(нужно будет очистить комнату после игры)
        int i;
        if (rooms[roomNumber].countGamers() == 1) {//если он там один, запускаем таймер(стремный таймер)
            i = 0;
        } else {
            i = waitTime[roomNumber];
        }
        for (; i < ServerConstants.WAITING_TIME; i++) {
            waitTime[roomNumber] = i;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                logger.debug(ex.getMessage());
            }
        }
        waitTime[roomNumber] = 0;//обнуляем таймер для этой комнаты
        if (rooms[roomNumber].countGamers() > 1) {//если игроков больше 1
//                                            dataE.writeBool(true);//разрешаем клиенту начать игру
            rooms[roomNumber].setPlaying(true);//устанавливаем, что в комнате идет игра
            
        } else {
//                                            dataE.writeBool(false);//иначе запрещаем начинать игру в этой комнате

        }

    }
    
    public int getWaitTime(int i) {
        return waitTime[i];
    }


    public RoomController getRooms(int i) {
        return rooms[i];
    }

    public void setRooms(RoomController rooms, int i) {
        this.rooms[i] = rooms;
    }
    
    
}

