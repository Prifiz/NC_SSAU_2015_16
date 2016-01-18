/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.net.server;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import team5.desktop.actions.DataExchange;
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
    
    private int[] waitTime;
    private GamerController gamer;
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
        DataExchange dataE = null;
        boolean f = true;
        try {
            dataE = new DataExchange(clientsocket.getInputStream(),clientsocket.getOutputStream());
            String comand = "";
            while (f) {
                comand = dataE.readString();
                
                switch (comand) {
                    //
                    //вход в игру
                    //
                    case "Login":
                        SignIn sign = new SignIn();
                        String login = dataE.readString();
                        dataE.write(sign.sign(login, dataE.readString()));
                        gamer = new GamerController(login);
                        break;
                    //
                    //регистрация
                    //
                    case "Registration":
                        Registration r = new Registration();
                        dataE.write(r.registrationUser(dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString()));
                        break;
                    //
                    //выбор комнаты и ожидание игроков
                    //    
                    case "Select":
                        String str = dataE.readString();
                        switch (str) {
                            case "Room 1":
                                if ((rooms[0].countGamers() < 4) && (rooms[0].isPlaying()==false)) {//если кол-во игроков в комнате меньше 4 и игра не начата то идем дальше
                                    dataE.write("Wait");//заставлем ждать
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
                                            dataE.write(true);//разрешаем клиенту начать игру
                                            rooms[0].setPlaying(true);//устанавливаем, что в комнате идет игра
                                        } else {
                                            dataE.write(false);//иначе запрещаем начинать игру в этой комнате
                                        }
                                    } else {
                                        for (int i = waitTime[0]; i < 30; i++) {//если же клиент не первый в этой комнате, запускаем таймер начиная с текущего значение(криво сделано, надо другое решение искать)
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        dataE.write(true);//разрешаем игру
                                    }
                                    
                                } else {
                                    dataE.write("Full");//если же первый if не выполнился, выводи сообщение
                                    dataE.write(false);//запрещаем игру
                                }
                                break;
                            case "Room 2":
                                if ((rooms[1].countGamers() < 4) && (rooms[1].isPlaying()==false)) {
                                    dataE.write("Wait");
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
                                            dataE.write(true);
                                            rooms[1].setPlaying(true);
                                        } else {
                                           dataE.write(false);
                                        }
                                    } else {
                                        for (int i = waitTime[1]; i < 30; i++) {
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        dataE.write(true);
                                    }
                                    
                                } else {
                                   dataE.write("Full");
                                   dataE.write(false);
                                }
                                break;
                            case "Room 3":
                                if ((rooms[2].countGamers() < 4) && (rooms[2].isPlaying()==false)) {
                                    dataE.write("Wait");
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
                                            dataE.write(true);
                                            rooms[2].setPlaying(true);
                                        } else {
                                            dataE.write(false);
                                        }
                                    } else {
                                        for (int i = waitTime[2]; i < 30; i++) {
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                        dataE.write(true);
                                    }
                                    
                                } else {
                                    dataE.write("Full");
                                    dataE.write(false);
                                }
                                break;
                            case "Room 4":
                                if ((rooms[3].countGamers() < 4) && (rooms[3].isPlaying()==false)) {
                                    dataE.write("Wait");
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
                                          dataE.write(true);
                                            rooms[3].setPlaying(true);
                                        } else {
                                          dataE.write(false);
                                        }
                                    } else {
                                        for (int i = waitTime[0]; i < 30; i++) {
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                log.debug(ex.getMessage());
                                            }
                                        }
                                     dataE.write(true);
                                    }
                                    
                                } else {
                                    dataE.write("Full");
                                    dataE.write(false);
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
