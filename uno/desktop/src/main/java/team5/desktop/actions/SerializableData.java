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

/**
 *
 * @author chanta
 */
public class SerializableData {
    public void serializableData(String fileName, Object data) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream (fileName));
        if (data instanceof WorkUser){
            oos.writeObject((WorkUser)data);  
        }
        //при создание класса работы с картами добавить сериализацию для них
        //else (data instanceof WorkCard){
        //    oos.writeObject((WorkCard)data);
        //}
        oos.close();
    }
    
    public WorkUser deserializableData(String fileName) throws IOException, ClassNotFoundException{
        WorkUser workUser;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        workUser = (WorkUser)ois.readObject();
        ois.close();
        return workUser;
    }
}
