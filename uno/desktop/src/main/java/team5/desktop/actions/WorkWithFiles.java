/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.actions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;
import java.util.ArrayList;
import team5.desktop.card.Card;
import team5.desktop.card.NumericCard;

/**
 *
 * @author chanta
 */
public class WorkWithFiles {

    public void serializableData(String fileName, Object data) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        if (data instanceof WorkUser) {
            oos.writeObject((WorkUser) data);
        }
        //при создание класса работы с картами добавить сериализацию для них
        else if (data instanceof WorkCard){
           oos.writeObject((WorkCard)data);
        }
        oos.close();
    }

    public WorkUser/* Object*/ deserializableData(String fileName) throws IOException, ClassNotFoundException {
        WorkUser workUser;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        workUser = (WorkUser) ois.readObject();
        ois.close();
        return workUser;
    }
    
    public static void writeCards(ArrayList<Card> pack, Writer out) throws IOException {
        for (Card card : pack) {
            out.write(card.toString());
        }
        out.flush();
    }

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
