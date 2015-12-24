/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author андрей
 */
public class Server {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(Server.class);
        ServerSocket s = null;
        try {
            s = new ServerSocket(8080);
        } catch (IOException e) {
            log.debug(e.getMessage());
        }

        while (true) {
            System.out.println("Waiting...");
            try {
                ServerThread thread = new ServerThread(s.accept());
                log.info("Client connected");
                thread.start();
            } catch (IOException ex) {
                log.debug(ex.getMessage());
            }
        }
    }
}
