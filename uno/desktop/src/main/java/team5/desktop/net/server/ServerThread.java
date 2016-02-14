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
import java.util.logging.Level;
import org.apache.log4j.Logger;
import team5.desktop.actions.Registration;
import team5.desktop.actions.SignIn;
import team5.desktop.exceptions.UserExistException;

/**
 *
 * @author андрей
 */
public class ServerThread extends Thread {

    private Logger log = Logger.getLogger(ServerThread.class);
    private Socket clientsocket;

    public ServerThread(Socket socket) {
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
                    case "Login":
                        SignIn sign = new SignIn();
                         {

                            out.writeBoolean(sign.sign(in.readUTF(), in.readUTF()));
                            out.flush();

                        }
                        break;
                    case "registration":
                        Registration r = new Registration();
                         {

                            out.writeBoolean(r.registrationUser(in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF()));
                            out.flush();

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
