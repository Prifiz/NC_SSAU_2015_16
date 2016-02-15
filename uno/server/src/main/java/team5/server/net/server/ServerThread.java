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
import team5.datamodel.actions.WorkUser;
import team5.server.actions.DataExchanger;
import team5.server.actions.GamerController;
import team5.server.actions.Registration;
import team5.server.actions.RoomController;
import team5.server.actions.SignIn;
import team5.server.actions.TableController;
import team5.datamodel.card.Card;
import team5.datamodel.card.NumericCard;
import team5.datamodel.exceptions.NotFoundException;
import team5.datamodel.exceptions.UserExistException;
import team5.datamodel.exceptions.UserNotFoundException;
import team5.datamodel.searches.Search;
import team5.datamodel.searches.UserSearch;
import team5.datamodel.transmissions.FileHandler;
import team5.datamodel.transmissions.Message;
import team5.datamodel.transmissions.MessageHandler;
import team5.datamodel.user.User;

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
    private MessageHandler messageHandler;

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
            messageHandler = new MessageHandler(clientsocket.getInputStream(), clientsocket.getOutputStream());
            dataE = new DataExchanger(clientsocket.getInputStream(), clientsocket.getOutputStream());

            String command;

            while (f) {
//                comand = dataE.readString();
                try {

                    clientRequest = messageHandler.receiveMessage();
                } catch (IOException ex) {
                    logger.debug(ex.getMessage());
                    f = false;
                } catch (JAXBException ex) {
                    logger.debug(ex.getMessage());
                    f = false;
                }
                command = clientRequest.getCommand();
                switch (command) {
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
                        //String str = dataE.readString();
                        String str = clientRequest.getChoice();
                        int roomNumber = Character.digit(str.charAt(5) - 1, 10);
                        selectRoom(roomNumber);
                        break;
                    case "UserTable":
                        userTableHandler();
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

    private void login() throws IOException {
        SignIn sign = new SignIn();
        String login = clientRequest.getUser().getServiceInfo().getLogin();
        serverResponse = new Message(sign.sign(login, clientRequest.getUser().getServiceInfo().getPassword()));
        try {
            messageHandler.sendMessage(serverResponse);
        } catch (JAXBException e) {
            logger.debug(e.getMessage());
        }
        gamer = new GamerController(login);
    }

    private void registration() throws UserExistException, IOException {
        Registration r = new Registration();
        try {
            boolean d = r.registrationUser(clientRequest.getUser().getPrivateInformation().getName(),
                    clientRequest.getUser().getPrivateInformation().getSurname(),
                    clientRequest.getUser().getAddress().getCountry(),
                    clientRequest.getUser().getAddress().getCity(),
                    clientRequest.getUser().getServiceInfo().getLogin(),
                    clientRequest.getUser().getServiceInfo().getPassword(),
                    clientRequest.getUser().getServiceInfo().getEmail(),
                    clientRequest.getUser().getServiceInfo().getDateOfRegistration());
            serverResponse = new Message(d);
            if (!d) {
                serverResponse.setChoice("Field");
            }
        } catch (UserExistException ex) {
            logger.debug(ex.getMessage());
            serverResponse = new Message(false);
            serverResponse.setChoice("User");
        }
        try {
            messageHandler.sendMessage(serverResponse);
        } catch (JAXBException e) {
            logger.debug(e.getMessage());
        }
        System.out.println("registration");
        try {
            WorkUser workUser = WorkUser.getWork();
            FileHandler workWithFiles = new FileHandler();
            //sd.serializableData("serializableData_WorkUser.bin", wu);
            workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
        } catch (JAXBException ex) {
            java.util.logging.Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);//TODO мы вроде другой логгер пользуем
        }
    }

    private void selectRoom(int roomNumber) throws IOException {
        if ((rooms[roomNumber].countGamers() < 4) && (rooms[roomNumber].isPlaying() == false)) {//если кол-во игроков в комнате меньше 4 и игра не начата то идем дальше
            //dataE.writeString("Wait");//заставлем ждать
            try {
                messageHandler.sendMessage(new Message("Wait"));
            } catch (JAXBException e) {
                logger.debug(e.getMessage());
            }
            rooms[roomNumber].addGamer(gamer);//добавляем игрока в комнату(нужно будет очистить комнату после игры)
            if (rooms[roomNumber].countGamers() == 1) {//если он там один, запускаем таймер(стремный таймер)
                for (int i = 0; i < 30; i++) {
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
                    try {
                        messageHandler.sendMessage(new Message(true));
                    } catch (JAXBException e) {
                        logger.debug(e.getMessage());
                    }
                    rooms[roomNumber].setPlaying(true);//устанавливаем, что в комнате идет игра
                    game(roomNumber, gamer);
                } else {
//                                            dataE.writeBool(false);//иначе запрещаем начинать игру в этой комнате
                    try {
                        messageHandler.sendMessage(new Message(false));
                    } catch (JAXBException e) {
                        logger.debug(e.getMessage());
                    }
                }
            } else {
                for (int i = waitTime[roomNumber]; i < 30; i++) {//если же клиент не первый в этой комнате, запускаем таймер начиная с текущего значение(криво сделано, надо другое решение искать)
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
                rooms[roomNumber].setPlaying(true);//устанавливаем, что в комнате идет игра
                game(roomNumber, gamer);
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
    }

    private void game(int roomNumber, GamerController gamer) {
        try {
            serverResponse = new Message();
            serverResponse.setChoice(gamer.getGamerLogin());
            serverResponse.setValue(rooms[roomNumber].countGamers());
            messageHandler.sendMessage(serverResponse);
            ArrayList<GamerController> gamers = rooms[roomNumber].getGamers();
            for (int i = 0; i < gamers.size(); i++) {
                serverResponse.setChoice(gamers.get(i).getGamerLogin());
                messageHandler.sendMessage(serverResponse);
            }
            TableController table = rooms[roomNumber].getTableController();
            Card card = null;
            for (int j = 0; j < 7; j++) {
                card = table.getCardFromPack();
                rooms[roomNumber].getGamer(gamer.getGamerLogin()).addCardToHand(card);
                messageHandler.sendMessage(new Message(card));
            }
            boolean f = true;
            while (f) {
                serverResponse = new Message();
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
                                serverResponse.setCommand(command);
                                break;
                            case "TakeCard":
                                serverResponse.setCommand(command);
                                order--;
                                break;
                            case "END TURN":
                                serverResponse.setCommand(command);
                                serverResponse.setCard(table.getLastCard());
                                serverResponse.setConfirmation(rooms[roomNumber].isFinish());
                                if (rooms[roomNumber].isFinish() == true) {
                                    return;
                                } else {
                                    break;
                                }
                            case "Exit":
                                serverResponse.setCommand(command);
                                break;
                        }
                        messageHandler.sendMessage(serverResponse);
                    }
                }
                if (order == rooms[roomNumber].getGamerNumber(gamer)) {
                    boolean game = true;
                    while (game == true) {
                        String command = "";
                        clientRequest = messageHandler.receiveMessage();
                        command = clientRequest.getCommand();
                        switch (command) {
                            case "Pass":
                                rooms[roomNumber].getGamer(order).setAct(command);
                                order++;
                                game = false;
                                break;
                            case "TakeCard":
                                card = table.getCardFromPack();
                                rooms[roomNumber].getGamer(gamer.getGamerLogin()).addCardToHand(card);
                                serverResponse.setCard(card);
                                messageHandler.sendMessage(serverResponse);
                                rooms[roomNumber].getGamer(order).setAct(command);
                                break;
                            case "END TURN":
                                card = rooms[roomNumber].getGamer(gamer.getGamerLogin()).searchCardInHand(clientRequest.getChoice());
                                serverResponse.setCard(card);
                                serverResponse.setConfirmation(table.isRightCard(card));
                                messageHandler.sendMessage(serverResponse);
                                if (table.isRightCard(card)) {
                                    table.setLastCard(card);
                                    rooms[roomNumber].getGamer(order).setAct(command);
                                    order++;
                                    game = false;
                                    boolean win = messageHandler.receiveMessage().getConfirmation();
                                    if (win == true) {
                                        rooms[roomNumber].setFinish(true);
                                        rooms[roomNumber].cleanRoom();
                                        return;
                                    }
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
                                serverResponse.setCommand(command);
                                break;
                            case "TakeCard":
                                serverResponse.setCommand(command);
                                order--;
                                break;
                            case "END TURN":
                                serverResponse.setCommand(command);
                                serverResponse.setCard(table.getLastCard());
                                serverResponse.setConfirmation(rooms[roomNumber].isFinish());
                                if (rooms[roomNumber].isFinish() == true) {
                                    return;
                                } else {
                                    break;
                                }
                            case "Exit":
                                serverResponse.setCommand(command);
                                break;
                        }
                        messageHandler.sendMessage(serverResponse);
                    }

                }
            }
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        } catch (JAXBException e) {
            logger.debug(e.getMessage());
        }
    }

    private void userTableHandler() {
        ArrayList<User> users = WorkUser.getWork().getArrOfUsers();
        try {
            messageHandler.sendMessage(new Message(users.size()));
            for (int i = 0; i < users.size(); i++) {
                messageHandler.sendMessage(new Message(users.get(i)));
            }
            boolean f = true;
            while (f) {
                clientRequest = messageHandler.receiveMessage();
                String command = clientRequest.getCommand();
                switch (command) {
                    case "SetUserInformation":
                        clientRequest = messageHandler.receiveMessage();
                        command = clientRequest.getCommand();
                        switch (command) {
                            case "Name":

                                users.get(clientRequest.getValue()).getPrivateInformation().setName(clientRequest.getChoice());
                                break;
                            case "Surname":
                                users.get(clientRequest.getValue()).getPrivateInformation().setSurname(clientRequest.getChoice());
                                break;
                            case "bDay":
                                users.get(clientRequest.getValue()).getPrivateInformation().setbDay(WorkUser.getWork().stringToLocalDate(clientRequest.getChoice()));
                            case "City":
                                users.get(clientRequest.getValue()).getAddress().setCity(clientRequest.getChoice());
                                break;
                            case "Country":
                                users.get(clientRequest.getValue()).getAddress().setCountry(clientRequest.getChoice());
                                break;
                            case "Email":
                                users.get(clientRequest.getValue()).getServiceInfo().setEmail(clientRequest.getChoice());
                                break;
                            case "Login":
                                users.get(clientRequest.getValue()).getServiceInfo().setLogin(clientRequest.getChoice());
                            //нужно написать обработку, когда лoгин не может быть изменен
                            case "Password":
                                users.get(clientRequest.getValue()).getServiceInfo().setPassword(clientRequest.getChoice());
                                break;
                        }
                        break;
                    case "Add":
                        User user = clientRequest.getUser();
                        WorkUser.getWork().addUser(clientRequest.getUser());
                        break;
                    case "Delete":
                        WorkUser.getWork().deleteUser(WorkUser.getWork().getArrOfUsers().get(clientRequest.getValue()).getServiceInfo().getLogin());
                        break;
                    case "Exit":
                        f = false;
                        break;

                }
                try {
                    WorkUser workUser = WorkUser.getWork();
                    FileHandler workWithFiles = new FileHandler();
                    workWithFiles.marshalData("marshalData_WorkUser.xml", workUser);
                } catch (JAXBException ex) {
                    java.util.logging.Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);//TODO мы вроде другой логгер пользуем
                }
            }
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        } catch (UserNotFoundException ex) {
            logger.debug(ex.getMessage());
        }
    }
}
