/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import team5.desktop.actions.GamerController;
import team5.desktop.actions.Registration;
import team5.desktop.actions.RoomController;
import team5.desktop.actions.SignIn;
import team5.desktop.exceptions.UserExistException;

/**
 *
 * @author андрей
 */
public class ServerThread extends Thread {
    
    int[] waitTime;
    GamerController gamer;
    private RoomController[] rooms;
    private Logger log = Logger.getLogger(ServerThread.class);
    private Socket clientsocket;
    
    public ServerThread(Socket socket, RoomController[] r, int[] time) {
        waitTime = time;
        rooms = r;
        this.clientsocket = socket;
    }
    
    @Override
    public void run() {
        DataInputStream in = null;
        DataOutputStream out = null;
        boolean f = true;
        try {
            in = new DataInputStream(clientsocket.getInputStream());
            out = new DataOutputStream(clientsocket.getOutputStream());
            String comand = "";
            while (f) {
                comand = in.readUTF();
                
                switch (comand) {
                    //
                    //вход в игру
                    //
                    case "Login":
                        SignIn sign = new SignIn();
                        String login = in.readUTF();
                        out.writeBoolean(sign.sign(login, in.readUTF()));
                        out.flush();
                        gamer = new GamerController(login);
                        break;
                    //
                    //регистрация
                    //
                    case "Registration":
                        Registration r = new Registration();
                        out.writeBoolean(r.registrationUser(in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF()));
                        out.flush();
                        break;
                    //
                    //выбор комнаты и ожидание игроков
                    //    
                    case "Select":
                        String str = in.readUTF();
                        switch (str) {
                            case "Room 1":
                                if ((rooms[0].countGamers() < 4) && (rooms[0].isPlaying()==false)) {//если кол-во игроков в комнате меньше 4 и игра не начата то идем дальше
                                    out.writeUTF("Wait");//заставлем ждать
                                    rooms[0].addGamer(gamer);//добавляем игрока в комнату(нужно будет очистить комнату после игры)
                                    if (rooms[0].countGamers() == 1) {//если он там один, запускаем таймер(стремный таймер)
                                        for (int i = 0; i < 30; i++) {
                                            waitTime[0] = i;
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        waitTime[0] = 0;//обнуляем таймер для этой комнаты
                                        if (rooms[0].countGamers() > 1) {//если игроков больше 1
                                            out.writeBoolean(true);//разрешаем клиенту начать игру
                                            rooms[0].setPlaying(true);//устанавливаем, что в комнате идет игра
                                        } else {
                                            out.writeBoolean(false);//иначе запрещаем начинать игру в этой комнате
                                        }
                                    } else {
                                        for (int i = waitTime[0]; i < 30; i++) {//если же клиент не первый в этой комнате, запускаем таймер начиная с текущего значение(криво сделано, надо другое решение искать)
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        out.writeBoolean(true);//разрешаем игру
                                    }
                                    
                                } else {
                                    out.writeUTF("Full");//если же первый if не выполнился, выводи сообщение
                                    out.writeBoolean(false);//запрещаем игру
                                }
                                break;
                            case "Room 2":
                                if ((rooms[1].countGamers() < 4) && (rooms[1].isPlaying()==false)) {
                                    rooms[1].addGamer(gamer);
                                    if (rooms[1].countGamers() == 1) {
                                        for (int i = 0; i < 30; i++) {
                                            waitTime[1] = i;
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        waitTime[1] = 0;
                                        if (rooms[1].countGamers() > 1) {
                                            out.writeBoolean(true);
                                            rooms[1].setPlaying(true);
                                        } else {
                                            out.writeBoolean(false);
                                        }
                                    } else {
                                        for (int i = waitTime[1]; i < 30; i++) {
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        out.writeBoolean(true);
                                    }
                                    
                                } else {
                                    out.writeUTF("Full");
                                    out.writeBoolean(false);
                                }
                                break;
                            case "Room 3":
                                if ((rooms[2].countGamers() < 4) && (rooms[2].isPlaying()==false)) {
                                    rooms[2].addGamer(gamer);
                                    if (rooms[2].countGamers() == 1) {
                                        for (int i = 0; i < 30; i++) {
                                            waitTime[2] = i;
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        waitTime[2] = 0;
                                        if (rooms[2].countGamers() > 1) {
                                            out.writeBoolean(true);
                                            rooms[2].setPlaying(true);
                                        } else {
                                            out.writeBoolean(false);
                                        }
                                    } else {
                                        for (int i = waitTime[2]; i < 30; i++) {
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        out.writeBoolean(true);
                                    }
                                    
                                } else {
                                    out.writeUTF("Full");
                                    out.writeBoolean(false);
                                }
                                break;
                            case "Room 4":
                                if ((rooms[3].countGamers() < 4) && (rooms[3].isPlaying()==false)) {
                                    rooms[3].addGamer(gamer);
                                    if (rooms[3].countGamers() == 1) {
                                        for (int i = 0; i < 30; i++) {
                                            waitTime[3] = i;
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        waitTime[3] = 0;
                                        if (rooms[3].countGamers() > 1) {
                                            out.writeBoolean(true);
                                            rooms[3].setPlaying(true);
                                        } else {
                                            out.writeBoolean(false);
                                        }
                                    } else {
                                        for (int i = waitTime[0]; i < 30; i++) {
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        out.writeBoolean(true);
                                    }
                                    
                                } else {
                                    out.writeUTF("Full");
                                    out.writeBoolean(false);
                                }
                                break;
                            
                        }
                    
                }
            }
        } catch (IOException ex) {
            f = false;
            log.debug(ex.getMessage());
        } catch (UserExistException ex) {
            f = false;
            log.debug(ex.getMessage());
        }
    }
}
