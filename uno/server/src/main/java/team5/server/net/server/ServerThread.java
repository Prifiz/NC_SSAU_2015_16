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
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.server.actions.DataExchanger;
import team5.server.actions.GamerController;
import team5.server.actions.Registration;
import team5.server.actions.RoomController;
import team5.server.actions.SignIn;
import team5.server.actions.TableController;
import team5.datamodel.card.Card;
import team5.datamodel.exceptions.UserExistException;
import team5.datamodel.transmissions.Message;
import team5.datamodel.transmissions.MessageHandler;

/**
 *
 * @author андрей
 */
public class ServerThread extends Thread {

    private int[] waitTime;
    private GamerController gamer;
    private RoomController[] rooms;
    private Logger logger = Logger.getLogger(ServerThread.class);
    private Socket clientsocket;
    private DataExchanger dataE;
    private Message clientRequest;
    private Message serverResponse;

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
            MessageHandler messageHandler = new MessageHandler(clientsocket.getInputStream(), clientsocket.getOutputStream());
            dataE = new DataExchanger(clientsocket.getInputStream(), clientsocket.getOutputStream());

            String comand = "";

            while (f) {
//                comand = dataE.readString();
                System.out.println("Новый заход");
                try {

                    clientRequest = messageHandler.receiveMessage();
                } catch (IOException ex) {
                    logger.debug(ex.getMessage());
                } catch (JAXBException ex) {
                    logger.debug(ex.getMessage());
                }
                comand = clientRequest.getCommand();
                switch (comand) {
                    //
                    //вход в игру
                    //
                    case "Login":

                        SignIn sign = new SignIn();
                        String login = clientRequest.getUser().getServiceInfo().getLogin();
                        serverResponse = new Message(sign.sign(login, clientRequest.getUser().getServiceInfo().getPassword()));
                        try {
                            messageHandler.sendMessage(serverResponse);
                        } catch (JAXBException e) {
                            logger.debug(e.getMessage());
                        }
                        gamer = new GamerController(login);
                        break;
                    //
                    //регистрация
                    //
                    case "Registration":
                        Registration r = new Registration();
                        serverResponse = new Message(r.registrationUser(clientRequest.getUser().getPrivateInformation().getName(),
                                clientRequest.getUser().getPrivateInformation().getSurname(),
                                clientRequest.getUser().getAddress().getCountry(),
                                clientRequest.getUser().getAddress().getCity(),
                                clientRequest.getUser().getServiceInfo().getLogin(),
                                clientRequest.getUser().getServiceInfo().getPassword(),
                                clientRequest.getUser().getServiceInfo().getEmail(),
                                clientRequest.getUser().getServiceInfo().getDateOfRegistration()));
//                        dataE.writeBool(r.registrationUser(dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString(), 
//                                dataE.readString(), dataE.readString(), dataE.readString(), dataE.readString()));
                        try {
                            messageHandler.sendMessage(serverResponse);
                        } catch (JAXBException e) {
                            logger.debug(e.getMessage());
                        }
                        break;
                    //
                    //выбор комнаты и ожидание игроков
                    //    
                    case "Select":
                        //String str = dataE.readString();
                        String str = clientRequest.getChoice();
                        switch (str) {
                            case "Room 1":
                                if ((rooms[0].countGamers() < 4) && (rooms[0].isPlaying() == false)) {//если кол-во игроков в комнате меньше 4 и игра не начата то идем дальше
                                    //dataE.writeString("Wait");//заставлем ждать
                                    try {
                                        messageHandler.sendMessage(new Message("Wait"));
                                    } catch (JAXBException e) {
                                        logger.debug(e.getMessage());
                                    }
                                    rooms[0].addGamer(gamer);//добавляем игрока в комнату(нужно будет очистить комнату после игры)
                                    if (rooms[0].countGamers() == 1) {//если он там один, запускаем таймер(стремный таймер)
                                        for (int i = 0; i < 30; i++) {
                                            waitTime[0] = i;
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                logger.debug(ex.getMessage());
                                            }
                                        }
                                        waitTime[0] = 0;//обнуляем таймер для этой комнаты
                                        if (rooms[0].countGamers() > 1) {//если игроков больше 1
//                                            dataE.writeBool(true);//разрешаем клиенту начать игру
                                            try {
                                                messageHandler.sendMessage(new Message(true));
                                            } catch (JAXBException e) {
                                                logger.debug(e.getMessage());
                                            }
                                            rooms[0].setPlaying(true);//устанавливаем, что в комнате идет игра
                                            game(0, gamer);
                                        } else {
//                                            dataE.writeBool(false);//иначе запрещаем начинать игру в этой комнате
                                            try {
                                                messageHandler.sendMessage(new Message(false));
                                            } catch (JAXBException e) {
                                                logger.debug(e.getMessage());
                                            }
                                        }
                                    } else {
                                        for (int i = waitTime[0]; i < 30; i++) {//если же клиент не первый в этой комнате, запускаем таймер начиная с текущего значение(криво сделано, надо другое решение искать)
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                logger.debug(ex.getMessage());
                                            }
                                        }
//                                        dataE.writeBool(true);//разрешаем игру
                                        try {
                                            messageHandler.sendMessage(new Message(true));
                                        } catch (JAXBException e) {
                                            logger.debug(e.getMessage());
                                        }
                                        rooms[0].setPlaying(true);//устанавливаем, что в комнате идет игра
                                        game(0, gamer);
                                    }

                                } else {
//                                    dataE.writeString("Full");//если же первый if не выполнился, выводи сообщение
//                                    dataE.writeBool(false);//запрещаем игру
                                    serverResponse = new Message("Full");
                                    serverResponse.setConfirmation(false);
                                    try {
                                        messageHandler.sendMessage(serverResponse);
                                    } catch (JAXBException e) {
                                        logger.debug(e.getMessage());
                                    }

                                }
                                break;
                            case "Room 2":
                                if ((rooms[1].countGamers() < 4) && (rooms[1].isPlaying() == false)) {
                                    //dataE.writeString("Wait");//заставлем ждать
                                    try {
                                        messageHandler.sendMessage(new Message("Wait"));
                                    } catch (JAXBException e) {
                                        logger.debug(e.getMessage());
                                    }
                                    rooms[1].addGamer(gamer);
                                    if (rooms[1].countGamers() == 1) {
                                        for (int i = 0; i < 30; i++) {
                                            waitTime[1] = i;
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                logger.debug(ex.getMessage());
                                            }
                                        }
                                        waitTime[1] = 0;
                                        if (rooms[1].countGamers() > 1) {
//                                            dataE.writeBool(true);//разрешаем клиенту начать игру
                                            try {
                                                messageHandler.sendMessage(new Message(true));
                                            } catch (JAXBException e) {
                                                logger.debug(e.getMessage());
                                            }
                                            rooms[1].setPlaying(true);
                                        } else {

//                                            dataE.writeBool(false);//иначе запрещаем начинать игру в этой комнате
                                            try {
                                                messageHandler.sendMessage(new Message(false));
                                            } catch (JAXBException e) {
                                                logger.debug(e.getMessage());
                                            }
                                        }
                                    } else {
                                        for (int i = waitTime[1]; i < 30; i++) {
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                logger.debug(ex.getMessage());
                                            }
                                        }

//                                        dataE.writeBool(true);//разрешаем игру
                                        try {
                                            messageHandler.sendMessage(new Message(true));
                                        } catch (JAXBException e) {
                                            logger.debug(e.getMessage());
                                        }
                                    }

                                } else {
//                                    dataE.writeString("Full");//если же первый if не выполнился, выводи сообщение
//                                    dataE.writeBool(false);//запрещаем игру
                                    serverResponse = new Message("Full");
                                    serverResponse.setConfirmation(false);
                                    try {
                                        messageHandler.sendMessage(serverResponse);
                                    } catch (JAXBException e) {
                                        logger.debug(e.getMessage());
                                    }
                                }
                                break;
                            case "Room 3":
                                if ((rooms[2].countGamers() < 4) && (rooms[2].isPlaying() == false)) {
                                    //dataE.writeString("Wait");//заставлем ждать
                                    try {
                                        messageHandler.sendMessage(new Message("Wait"));
                                    } catch (JAXBException e) {
                                        logger.debug(e.getMessage());
                                    }
                                    rooms[2].addGamer(gamer);
                                    if (rooms[2].countGamers() == 1) {
                                        for (int i = 0; i < 30; i++) {
                                            waitTime[2] = i;
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                logger.debug(ex.getMessage());
                                            }
                                        }
                                        waitTime[2] = 0;
                                        if (rooms[2].countGamers() > 1) {

//                                            dataE.writeBool(true);//разрешаем клиенту начать игру
                                            try {
                                                messageHandler.sendMessage(new Message(true));
                                            } catch (JAXBException e) {
                                                logger.debug(e.getMessage());
                                            }
                                            rooms[2].setPlaying(true);
                                        } else {

//                                            dataE.writeBool(false);//иначе запрещаем начинать игру в этой комнате
                                            try {
                                                messageHandler.sendMessage(new Message(false));
                                            } catch (JAXBException e) {
                                                logger.debug(e.getMessage());
                                            }
                                        }
                                    } else {
                                        for (int i = waitTime[2]; i < 30; i++) {
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                logger.debug(ex.getMessage());
                                            }
                                        }

//                                        dataE.writeBool(true);//разрешаем игру
                                        try {
                                            messageHandler.sendMessage(new Message(true));
                                        } catch (JAXBException e) {
                                            logger.debug(e.getMessage());
                                        };
                                    }

                                } else {
//                                    dataE.writeString("Full");//если же первый if не выполнился, выводи сообщение
//                                    dataE.writeBool(false);//запрещаем игру
                                    serverResponse = new Message("Full");
                                    serverResponse.setConfirmation(false);
                                    try {
                                        messageHandler.sendMessage(serverResponse);
                                    } catch (JAXBException e) {
                                        logger.debug(e.getMessage());
                                    }
                                }
                                break;
                            case "Room 4":
                                if ((rooms[3].countGamers() < 4) && (rooms[3].isPlaying() == false)) {
                                    //dataE.writeString("Wait");//заставлем ждать
                                    try {
                                        messageHandler.sendMessage(new Message("Wait"));
                                    } catch (JAXBException e) {
                                        logger.debug(e.getMessage());
                                    }
                                    rooms[3].addGamer(gamer);
                                    if (rooms[3].countGamers() == 1) {
                                        for (int i = 0; i < 30; i++) {
                                            waitTime[3] = i;
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                logger.debug(ex.getMessage());
                                            }
                                        }
                                        waitTime[3] = 0;
                                        if (rooms[3].countGamers() > 1) {

//                                            dataE.writeBool(true);//разрешаем клиенту начать игру
                                            try {
                                                messageHandler.sendMessage(new Message(true));
                                            } catch (JAXBException e) {
                                                logger.debug(e.getMessage());
                                            }
                                            rooms[3].setPlaying(true);
                                        } else {

//                                            dataE.writeBool(false);//иначе запрещаем начинать игру в этой комнате
                                            try {
                                                messageHandler.sendMessage(new Message(false));
                                            } catch (JAXBException e) {
                                                logger.debug(e.getMessage());
                                            }
                                        }
                                    } else {
                                        for (int i = waitTime[0]; i < 30; i++) {
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException ex) {
                                                logger.debug(ex.getMessage());
                                            }
                                        }

//                                        dataE.writeBool(true);//разрешаем игру
                                        try {
                                            messageHandler.sendMessage(new Message(true));
                                        } catch (JAXBException e) {
                                            logger.debug(e.getMessage());
                                        }
                                    }

                                } else {
//                                    dataE.writeString("Full");//если же первый if не выполнился, выводи сообщение
//                                    dataE.writeBool(false);//запрещаем игру
                                    serverResponse = new Message("Full");
                                    serverResponse.setConfirmation(false);
                                    try {
                                        messageHandler.sendMessage(serverResponse);
                                    } catch (JAXBException e) {
                                        logger.debug(e.getMessage());
                                    }
                                }
                                break;

                        }

                }
            }
        } catch (IOException ex) {
            f = false;
            logger.debug(ex.getMessage());
        } catch (UserExistException ex) {
            f = false;
            logger.debug(ex.getMessage());
        }
    }

    private void game(int r, GamerController gamer) {
        try {
            dataE.writeString(gamer.getGamerLogin());
            int k = rooms[r].countGamers();
            dataE.writeInt(k);
            ArrayList<GamerController> gamers = rooms[r].getGamers();
            for (int i = 0; i < gamers.size(); i++) {
                dataE.writeString(gamers.get(i).getGamerLogin());
            }
            TableController table = rooms[r].getTableController();
            Card card = null;
            for (int j = 0; j < 7; j++) {
                card = table.getCardFromPack();
                rooms[r].getGamer(gamer.getGamerLogin()).addCardToHand(card);
                dataE.writeInt(card.getIcon());
                dataE.writeString(card.getColor());
            }
            boolean f = true;
            while (f) {
                int order = 0;
                if (order < rooms[r].getGamerNumber(gamer)) {
                    for (; order < rooms[r].getGamerNumber(gamer); order++) {
                        String command = null;
                        while (rooms[r].getGamer(order).getAct() == null) {
                            yield();
                        }
                        command = rooms[r].getGamer(order).getAct();
                        rooms[r].getGamer(order).setReadCount(rooms[r].getGamer(order).getReadCount() + 1);
                        if (rooms[r].getGamer(order).getReadCount() == rooms[r].countGamers() - 1) {
                            rooms[r].getGamer(order).setAct(null);
                            rooms[r].getGamer(order).setReadCount(0);
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
                                dataE.writeBool(rooms[r].isFinish());
                                if (rooms[r].isFinish() == true) {
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
                if (order == rooms[r].getGamerNumber(gamer)) {
                    boolean game = true;
                    while (game == true) {
                        String command = null;
                        command = dataE.readString();
                        switch (command) {
                            case "Pass":
                                rooms[r].getGamer(order).setAct(command);
                                order++;
                                game = false;
                                break;
                            case "TakeCard":
                                card = table.getCardFromPack();
                                rooms[r].getGamer(gamer.getGamerLogin()).addCardToHand(card);
                                dataE.writeInt(card.getIcon());
                                dataE.writeString(card.getColor());
                                rooms[r].getGamer(order).setAct(command);
                                break;
                            case "END TURN":
                                card = rooms[r].getGamer(gamer.getGamerLogin()).searchCardInHand(dataE.readString());
                                dataE.writeInt(card.getIcon());
                                dataE.writeString(card.getColor());
                                if (table.isRightCard(card)) {
                                    dataE.writeBool(table.isRightCard(card));
                                    table.setLastCard(card);
                                    rooms[r].getGamer(order).setAct(command);
                                    order++;
                                    game = false;
                                    boolean win = dataE.readBool();
                                    if (win == true) {
                                        rooms[r].setFinish(true);
                                        rooms[r].cleanRoom();
                                        return;
                                    }
                                } else {
                                    dataE.writeBool(table.isRightCard(card));
                                }
                                break;
                            case "Exit":
                                rooms[r].getGamer(order).setAct(command);
                                rooms[r].removeGamer(gamer);
                                if (rooms[r].countGamers() == 0) {
                                    rooms[r].cleanRoom();
                                }
                                game = false;
                                return;
                        }
                    }
                }
                if (order > rooms[r].getGamerNumber(gamer)) {
                    for (; order < rooms[r].countGamers(); order++) {
                        String command = null;
                        while (rooms[r].getGamer(order).getAct() == null) {
                            yield();
                        }
                        command = rooms[r].getGamer(order).getAct();
                        rooms[r].getGamer(order).setReadCount(rooms[r].getGamer(order).getReadCount() + 1);
                        if (rooms[r].getGamer(order).getReadCount() == rooms[r].countGamers() - 1) {
                            rooms[r].getGamer(order).setAct(null);
                            rooms[r].getGamer(order).setReadCount(0);
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
                                dataE.writeBool(rooms[r].isFinish());
                                if (rooms[r].isFinish() == true) {
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
            logger.debug(ex.getMessage());
        }
    }
}
