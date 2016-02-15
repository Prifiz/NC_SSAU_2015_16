package team5.datamodel.transmissions;

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
import java.io.Writer;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import team5.datamodel.actions.WorkCard;
import team5.datamodel.actions.WorkUser;
import team5.datamodel.card.Card;
import team5.datamodel.card.NumericCard;

/**
 *
 * @author chanta
 */
public class FileHandler {

    private static final FileHandler fileHandler = new FileHandler();
    private File file = null;
    
    public static FileHandler getFileHandler(){
        return fileHandler;
    }
    
    private FileHandler(){
        
    }

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
    public static WorkUser/* Object*/ deserializableData(String fileName) throws IOException, ClassNotFoundException {
        WorkUser workUser;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        workUser = (WorkUser) ois.readObject();
        ois.close();
        return workUser;
    }

    /**
     * This demethod marshales the data WorkUser file's data in type WorkUser
     *
     * @param fileName
     * @return workUser
     * @throws JAXBException
     */
    public WorkUser unmarshalData(String fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(WorkUser.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        WorkUser workUser = null;
        if (file == null) {
            workUser = (WorkUser) unmarshaller.unmarshal(new File(fileName));
        } else {
            workUser = (WorkUser) unmarshaller.unmarshal(file);
        }
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
        if (file == null) {
            marshaller.marshal(workUser, new File(fileName));
        } else {
            marshaller.marshal(workUser, file);
        }

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

    public  File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
