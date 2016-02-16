/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datamodel.transmissions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Class for send and receive messages between client and server
 *
 * @author Dmitry
 */
public class MessageHandler {

    private StringWriter writer;
    private StringReader reader;

    private DataInputStream din;
    private DataOutputStream dout;

    public MessageHandler(InputStream in, OutputStream out) {
        this.din = new DataInputStream(in);
        this.dout = new DataOutputStream(out);
    }

    /**
     * Method send message object
     *
     *
     * @param message
     * @throws JAXBException
     * @throws IOException
     */
    public void sendMessage(Message message) throws JAXBException, IOException {
        writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(message.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(message, writer);
        dout.writeUTF(writer.toString());
        dout.flush();
    }

    /**
     * Method receive message object
     *
     * @return Message
     * @throws JAXBException
     * @throws IOException
     */
    public Message receiveMessage() throws JAXBException, IOException {
        reader = new StringReader(din.readUTF());
        JAXBContext context = JAXBContext.newInstance(Message.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Message message = (Message) unmarshaller.unmarshal(reader);
        return message;
    }

}
