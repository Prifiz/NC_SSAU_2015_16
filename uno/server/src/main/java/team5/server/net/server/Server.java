/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.net.server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDate;
import javax.swing.JFileChooser;
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
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Select a user file");
        FileHandler workWithFiles = FileHandler.getFileHandler();
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            workWithFiles.setFile(file);
        }
        try {
            WorkUser workUser = WorkUser.getWork();
            workUser.addWorkUser(workWithFiles.unmarshalData("marshalData_WorkUser.xml"));

        } catch (JAXBException ex) {
            logger.debug(ex.getMessage());
        }
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
