/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.client.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.log4j.Logger;
import team5.datamodel.transmissions.MessageHandler;

/**
 * class includes IP adress, port,in\out streams, etc
 *
 * @author Dmitry
 */
public class ClientSocket {

    private int serverport;
    private String address;
    private InputStream in;
    private OutputStream out;
    private Logger logger = Logger.getLogger(ClientSocket.class);
    private MessageHandler messageHandler;

    /**
     * Create ClientSocket with default serverport = 8081 and IP address
     * 127.0.0.1
     */
    public ClientSocket() {
        serverport = 8081;
        address = /*"192.168.43.197"*/ "127.0.0.1";
        in = null;
        out = null;

    }

    /**
     * Create ClientSocket
     *
     * @param serverPort
     * @param address
     */
    public ClientSocket(int serverPort, String address) {
        this.serverport = serverport;
        this.address = address;
        in = null;
        out = null;
    }

    public InputStream getInputStream() {
        return in;
    }

    public OutputStream getOutputStream() {
        return out;
    }
    
    public MessageHandler getMessageHandler(){
        return messageHandler;
    }

    /**
     * Create connection with server
     *
     * @throws IOException
     */
    public void connection()
            throws IOException {
        InetAddress inetAdress = InetAddress.getByName(address);
        Socket socket = new Socket(inetAdress, serverport);
        in = socket.getInputStream();
        out = socket.getOutputStream();
        logger.info("Connect to server");
        messageHandler=new MessageHandler(in, out);
    }
}
