package team5.library.transmissions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import team5.library.actions.WorkCard;
import team5.library.actions.WorkUser;
import team5.library.card.Card;
import team5.library.card.NumericCard;

/**
 *
 * @author chanta
 */
public class WorkWithFiles {

    /**
     * This method serializes the data WorkUser in the file
     *
     * @param fileName
     * @param data
     * @throws IOException
     */
    public void serializableData(String fileName, Object data) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        if (data instanceof WorkUser) {
            oos.writeObject((WorkUser) data);
        } //при создание класса работы с картами добавить сериализацию для них
        else if (data instanceof WorkCard) {
            oos.writeObject((WorkCard) data);
        }
        oos.close();
    }

    /**
     * This demethod serializes the data WorkUser file's data in type WorkUser
     *
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public WorkUser/* Object*/ deserializableData(String fileName) throws IOException, ClassNotFoundException {
        WorkUser workUser;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        workUser = (WorkUser) ois.readObject();
        ois.close();
        return workUser;
    }

    /**
     * This demethod marshales the data WorkUser file's data in type WorkUser
     *
     * @param fileNme
     * @return workUser
     * @throws JAXBException
     */
    public WorkUser unmarshalData(String fileNme) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(WorkUser.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        WorkUser workUser = (WorkUser) unmarshaller.unmarshal(new File(fileNme));
        return workUser;
    }

    /**
     * This demethod marshales the data WorkUser file's data in type WorkUser
     *
     * @param fileName
     * @param workUser
     * @throws JAXBException
     */
    public void marshalData(String fileName, WorkUser workUser) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(workUser.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(workUser, new File(fileName));

    }

    /**
     * Method marshalles Commands object and write it to stream
     *
     * @param writer
     * @param message
     * @throws JAXBException
     */
    public static void marshalData(StringWriter writer, Message message) throws JAXBException {
        //TODO пока сделал статик. не решил как лучше
        JAXBContext context = JAXBContext.newInstance(message.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(message, new File("marsh.xml"));
         marshaller.marshal(message, writer);
    }
    
     public static StringWriter marshalData( Message request) throws JAXBException {
        //TODO пока сделал статик. не решил как лучше
        StringWriter writer=new StringWriter();
        JAXBContext context = JAXBContext.newInstance(request.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(request, new File("marsh.xml"));
         marshaller.marshal(request, writer);
         return writer;
    }

    /**
     * Method unmarshalles Commands object from stream
     *
     * @param reader
     * @return Request
     * @throws JAXBException
     */
    public static Message unmarshalData(StringReader reader) throws JAXBException {
        //TODO пока сделал статик. не решил как лучше
        JAXBContext context = JAXBContext.newInstance(Message.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
//        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        File file=new File("requestXmlSchema.xsd");
//        Schema schema = schemaFactory.newSchema(file);
//        unmarshaller.setSchema(schema);
        Message command = (Message) unmarshaller.unmarshal(reader);
        return command;
    }
    //эксперимент
    public static Message unmarshalData(XMLStreamReader inputStream) throws JAXBException {
        //TODO пока сделал статик. не решил как лучше
        JAXBContext context = JAXBContext.newInstance(Message.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
//        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        File file=new File("requestXmlSchema.xsd");
//        Schema schema = schemaFactory.newSchema(file);
//        unmarshaller.setSchema(schema);
        Message command = (Message) unmarshaller.unmarshal(inputStream);
        return command;
    }

    /**
     * This method write cards to text file look as: "Type of card" "color of
     * card" "int value of card".
     *
     * @param pack
     * @param out
     * @throws IOException
     */
    public static void writeCards(ArrayList<Card> pack, Writer out) throws IOException {
        for (Card card : pack) {
            out.write(card.toString());
        }
        out.flush();
    }

    /**
     * This method read cards from text file and return list of cards. Records
     * in the file should look as: "Type of cad" "color of card" "int value of
     * card".
     *
     * @param in
     * @return ArrayList<Card>
     * @throws IOException
     */
    public static ArrayList<Card> readCards(Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        Card card = null;
        ArrayList<Card> pack = new ArrayList();
        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            String type = st.sval;
            st.nextToken();
            String color = st.sval;
            st.nextToken();
            int icon = (int) st.nval;
            if (type.equals("Numeric")) {
                card = new NumericCard(icon, color);
            }
            pack.add(card);
        }
        return pack;
    }
}
