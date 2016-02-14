/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDate;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import team5.server.actions.RoomController;
import team5.datamodel.actions.WorkUser;
import team5.datamodel.transmissions.FileHandler;
import team5.datamodel.exceptions.UserExistException;

/**
 *
 * @author андрей
 */
public class Server {

    public static void main(String[] args) {
        RoomController[] rooms = new RoomController[4];
        int[] waitTime = new int[4];
        for (int i = 0; i < 4; i++) {
            rooms[i] = new RoomController();
        }
        Logger logger = Logger.getLogger(Server.class);
        try {
            WorkUser workUser = WorkUser.getWork();
            FileHandler workWithFiles = new FileHandler();
            //wu.addWorkUser(sd.deserializableData("serializableData_WorkUser.bin"));
            workUser.addWorkUser(workWithFiles.unmarshalData("marshalData_WorkUser.xml"));

        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        }
        WorkUser wu = WorkUser.getWork();
//        try {
////            wu.addUser("putin", "d", "dfkmv", "dfkv", "ldkf", "ld", "adsc", LocalDate.parse("19.12.2333"));
////            wu.addUser("medved", "s", "sdfd", "sfdd", "fsdsf", "fdd", "fdd", LocalDate.parse("19.12.12"));
////            wu.addUser("sasdq", "d", "d", "d", "d", "d", "d", LocalDate.parse("19.12.12"));
////            wu.addUser("f", "d", "d", "d", "sfsdf", "d", "d", LocalDate.parse("19.12.12"));
////            wu.addUser("d", "d", "d", "d", "dfgf", "d", "d", LocalDate.parse("19.12.12"));
//            //wu.addUser("asda", "d", "d", "d", "d", "d", "d", "19.12.12");
//
//        } catch (UserExistException e) {
//            logger.debug(e.getMessage());
//        }

        ServerSocket s = null;
        try {
            s = new ServerSocket(8081);
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }

        while (true) {
            System.out.println("Waiting...");
            try {
                ServerThread thread = new ServerThread(s.accept(), rooms, waitTime);
                logger.info("Client connected");
                thread.start();
            } catch (IOException ex) {
                logger.debug(ex.getMessage());
            }
        }
    }
}
