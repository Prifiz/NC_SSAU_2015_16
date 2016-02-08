/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.net.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.library.actions.WorkUser;
import team5.server.actions.DataExchanger;
import team5.server.actions.GamerController;
import team5.server.actions.Registration;
import team5.server.actions.RoomController;
import team5.server.actions.SignIn;
import team5.server.actions.TableController;
import team5.library.card.Card;
import team5.library.exceptions.UserExistException;
import team5.library.transmissions.Commands;

/**
 *
 * @author андрей
 */
public class ServerThread extends Thread {

    private int[] waitTime;
    private GamerController gamer;
    private RoomController[] rooms;
    private Logger log = Logger.getLogger(ServerThread.class);
    private Socket clientsocket;
    private DataExchanger dataE;
    private Commands command;

    public ServerThread(Socket socket, RoomController[] r, int[] time) {
        waitTime = time;
        rooms = r;
        this.clientsocket = socket;
    }

    @Override
    public void run() {
        dataE = null;
        boolean f = true;
        try {
            dataE = new DataExchanger(clientsocket.getInputStream(), clientsocket.getOutputStream());

            String comand;

            while (f) {
                comand = dataE.readString();
                switch (comand) {
                    //
                    //вход в игру
                    //
                    case "Login":
                        login();
                        break;
                    //
                    //регистрация
                    //
                    case "Registration":
                        registration();
                        break;
                    //
                    //выбор комнаты и ожидание игроков
                    //    

                    case "Select":
                        String str = dataE.readString();
                        int roomNumber = Character.digit(str.charAt(5) - 1, 10);
                        selectRoom(roomNumber);
                        break;
                }
            }
        } catch (IOException ex) {
            f = false;
            log.debug(ex.getMessage());
        }
    }

    private void login() throws IOException {
        SignIn sign = new SignIn();
        String login = dataE.readString();
        dataE.writeBool(sign.sign(login, dataE.readString()));
        gamer = new GamerController(login);
    }

    private void registration() throws IOException  {
        Registration r = new Registration();
        try {
            boolean d = r.registrationUser(dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString());
            dataE.writeBool(d);
            if (!d) {
                dataE.writeString("Field");
            }
        } catch (UserExistException ex) {
            log.debug(ex.getMessage());
            dataE.writeBool(false);
            dataE.writeString("User");
        }
        try {
            WorkUser workUser = WorkUser.getWork();
            FileHandler workWithFiles = new FileHandler();
            //sd.serializableData("serializableData_WorkUser.bin", wu);
            workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
        } catch (JAXBException ex) {
            java.util.logging.Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void selectRoom(int roomNumber) throws IOException {
        if ((rooms[roomNumber].countGamers() < 4) && (rooms[roomNumber].isPlaying() == false)) {//если кол-во игроков в комнате меньше 4 и игра не начата то идем дальше
            dataE.writeString("Wait");//заставлем ждать
            rooms[roomNumber].addGamer(gamer);//добавляем игрока в комнату(нужно будет очистить комнату после игры)
            if (rooms[roomNumber].countGamers() == 1) {//если он там один, запускаем таймер(стремный таймер)
                for (int i = 0; i < 30; i++) {
                    waitTime[roomNumber] = i;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        log.debug(ex.getMessage());
                    }
                }
                waitTime[roomNumber] = 0;//обнуляем таймер для этой комнаты
                if (rooms[roomNumber].countGamers() > 1) {//если игроков больше 1
                    dataE.writeBool(true);//разрешаем клиенту начать игру
                    rooms[roomNumber].setPlaying(true);//устанавливаем, что в комнате идет игра
                    game(roomNumber, gamer);
                } else {
                    dataE.writeBool(false);//иначе запрещаем начинать игру в этой комнате
                }
            } else {
                for (int i = waitTime[roomNumber]; i < 30; i++) {//если же клиент не первый в этой комнате, запускаем таймер начиная с текущего значение(криво сделано, надо другое решение искать)
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        log.debug(ex.getMessage());
                    }
                }
                dataE.writeBool(true);//разрешаем игру
                rooms[roomNumber].setPlaying(true);//устанавливаем, что в комнате идет игра
                game(roomNumber, gamer);
            }

        } else {
            dataE.writeString("Full");//если же первый if не выполнился, выводи сообщение
            dataE.writeBool(false);//запрещаем игру
        }
    }

    private void game(int roomNumber, GamerController gamer) {
        try {
            dataE.writeString(gamer.getGamerLogin());
            int k = rooms[roomNumber].countGamers();
            dataE.writeInt(k);
            ArrayList<GamerController> gamers = rooms[roomNumber].getGamers();
            for (int i = 0; i < gamers.size(); i++) {
                dataE.writeString(gamers.get(i).getGamerLogin());
            }
            TableController table = rooms[roomNumber].getTableController();
            Card card = null;
            for (int j = 0; j < 7; j++) {
                card = table.getCardFromPack();
                rooms[roomNumber].getGamer(gamer.getGamerLogin()).addCardToHand(card);
                dataE.writeInt(card.getIcon());
                dataE.writeString(card.getColor());
            }
            boolean f = true;
            while (f) {
                int order = 0;
                if (order < rooms[roomNumber].getGamerNumber(gamer)) {
                    for (; order < rooms[roomNumber].getGamerNumber(gamer); order++) {
                        String command = null;
                        while (rooms[roomNumber].getGamer(order).getAct() == null) {
                            yield();
                        }
                        command = rooms[roomNumber].getGamer(order).getAct();
                        rooms[roomNumber].getGamer(order).setReadCount(rooms[roomNumber].getGamer(order).getReadCount() + 1);
                        if (rooms[roomNumber].getGamer(order).getReadCount() == rooms[roomNumber].countGamers() - 1) {
                            rooms[roomNumber].getGamer(order).setAct(null);
                            rooms[roomNumber].getGamer(order).setReadCount(0);
                        }
                        switch (command) {
                            case "Pass":
                                dataE.writeString(command);
                                break;
                            case "TakeCard":
                                dataE.writeString(command);
                                order--;
                                break;
                            case "END TURN":
                                dataE.writeString("END TURN");
                                dataE.writeInt(table.getLastCard().getIcon());
                                dataE.writeString(table.getLastCard().getColor());
                                dataE.writeBool(rooms[roomNumber].isFinish());
                                if (rooms[roomNumber].isFinish() == true) {
                                    return;
                                } else {
                                    break;
                                }
                            case "Exit":
                                dataE.writeString(command);
                                break;
                        }
                    }
                }
                if (order == rooms[roomNumber].getGamerNumber(gamer)) {
                    boolean game = true;
                    while (game == true) {
                        String command = null;
                        command = dataE.readString();
                        switch (command) {
                            case "Pass":
                                rooms[roomNumber].getGamer(order).setAct(command);
                                order++;
                                game = false;
                                break;
                            case "TakeCard":
                                card = table.getCardFromPack();
                                rooms[roomNumber].getGamer(gamer.getGamerLogin()).addCardToHand(card);
                                dataE.writeInt(card.getIcon());
                                dataE.writeString(card.getColor());
                                rooms[roomNumber].getGamer(order).setAct(command);
                                break;
                            case "END TURN":
                                card = rooms[roomNumber].getGamer(gamer.getGamerLogin()).searchCardInHand(dataE.readString());
                                dataE.writeInt(card.getIcon());
                                dataE.writeString(card.getColor());
                                if (table.isRightCard(card)) {
                                    dataE.writeBool(table.isRightCard(card));
                                    table.setLastCard(card);
                                    rooms[roomNumber].getGamer(order).setAct(command);
                                    order++;
                                    game = false;
                                    boolean win = dataE.readBool();
                                    if (win == true) {
                                        rooms[roomNumber].setFinish(true);
                                        rooms[roomNumber].cleanRoom();
                                        return;
                                    }
                                } else {
                                    dataE.writeBool(table.isRightCard(card));
                                }
                                break;
                            case "Exit":
                                rooms[roomNumber].getGamer(order).setAct(command);
                                rooms[roomNumber].removeGamer(gamer);
                                if (rooms[roomNumber].countGamers() == 0) {
                                    rooms[roomNumber].cleanRoom();
                                }
                                game = false;
                                return;
                        }
                    }
                }
                if (order > rooms[roomNumber].getGamerNumber(gamer)) {
                    for (; order < rooms[roomNumber].countGamers(); order++) {
                        String command = null;
                        while (rooms[roomNumber].getGamer(order).getAct() == null) {
                            yield();
                        }
                        command = rooms[roomNumber].getGamer(order).getAct();
                        rooms[roomNumber].getGamer(order).setReadCount(rooms[roomNumber].getGamer(order).getReadCount() + 1);
                        if (rooms[roomNumber].getGamer(order).getReadCount() == rooms[roomNumber].countGamers() - 1) {
                            rooms[roomNumber].getGamer(order).setAct(null);
                            rooms[roomNumber].getGamer(order).setReadCount(0);
                        }
                        switch (command) {
                            case "Pass":
                                dataE.writeString(command);
                                break;
                            case "TakeCard":
                                dataE.writeString(command);
                                order--;
                                break;
                            case "END TURN":
                                dataE.writeString("END TURN");
                                dataE.writeInt(table.getLastCard().getIcon());
                                dataE.writeString(table.getLastCard().getColor());
                                dataE.writeBool(rooms[roomNumber].isFinish());
                                if (rooms[roomNumber].isFinish() == true) {
                                    return;
                                } else {
                                    break;
                                }
                            case "Exit":
                                dataE.writeString(command);
                                break;
                        }
                    }

                }
            }
        } catch (IOException ex) {
            log.debug(ex.getMessage());
        }
    }
}
