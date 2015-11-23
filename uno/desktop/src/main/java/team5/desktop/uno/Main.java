/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.uno;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import team5.desktop.actions.SerializableData;
import team5.desktop.actions.WorkUser;
import team5.desktop.exceptions.*;
import team5.desktop.gui.StartFrame;
import team5.desktop.user.adress.Address;
import team5.desktop.searches.UserSearch;

/**
 *
 * @author chanta
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            WorkUser wu = WorkUser.getWork();
            SerializableData sd = new SerializableData();
            wu.addWorkUser(sd.deserializableData("serializableData_WorkUser.bin"));

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StartFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartFrame().setVisible(true);
            }
        });
       // ArrayList<User> users = null;
        // users.add(user1);
        WorkUser wu = WorkUser.getWork();
        try {
            wu.addUser("putin", "d", "d", "d", "sf", "d", "d", "19.12.12");
            wu.addUser("sasdq", "d", "d", "d", "d", "d", "d", "19.12.12");
            wu.addUser("f", "d", "d", "d", "sfsdf", "d", "d", "19.12.12");
            wu.addUser("d", "d", "d", "d", "putin", "d", "d", "19.12.12");
            //wu.addUser("asda", "d", "d", "d", "d", "d", "d", "19.12.12");
            wu.addUser("putin", "d", "d", "d", "putin ", "d", "d", "19.12.2333");

        } catch (UserExistException e) {

        }
        try {
            System.out.println("Search: " + UserSearch.Search("(\\d|\\s|\\w)").toString());

        } catch (UserNotFoundException e) {
            System.out.println("Ошибка!");
        }
        System.out.println(wu.viewUsers());

       // WorkUser wu =new WorkUser();
        //wu.addUser("Adam", "Smith", "USA","VS",new GregorianCalendar(1995, 12, 12) , "ggg", "1231114", "smi@gmai.com");
       // wu.addUser("bob1", "Clark", "Usa", "XX", "sf", "1234", "email@gmail.com",LocalDate.of(2014, 3, 4));
        // wu.addUser("bob2", "Clark", "Usa", "XX", "sf", "1234", "email@gmail.com", LocalDate.now());
        // wu.addUser("bob3", "Clark", "Usa", "XX", "sfsd", "1234", "email@gmail.com", LocalDate.now());
        // wu.addUser("bob4", "Clark", "Usa", "XX", "sfsdf", "1234", "email@gmail.com", LocalDate.now());
        // wu.addUser("bob5", "Clark", "Usa", "XX", "sfsdfa", "1234", "email@gmail.com", LocalDate.now());
//        wu.addUser("bob1", "Clark", "Usa", "XX", "sf", "1234", "email@gmail.com",LocalDate.of(2014, 3, 4));
//        wu.addUser("bob2", "Clark", "Usa", "XX", "sf", "1234", "email@gmail.com", LocalDate.now());
//        wu.addUser("bob3", "Clark", "Usa", "XX", "sfsd", "1234", "email@gmail.com", LocalDate.now());
//        wu.addUser("bob4", "Clark", "Usa", "XX", "sfsdf", "1234", "email@gmail.com", LocalDate.now());
//        wu.addUser("bob5", "Clark", "Usa", "XX", "sfsdfa", "1234", "email@gmail.com", LocalDate.now());
        //wu.deleteUser("sf");
        // System.out.println( wu.viewUsers());
        // SignIn in = new SignIn();
        //  char[] ch = new char[]{'1','2','3','4'};
        //String charToString = Character.toString(ch);
        // in.sign("sf",ch);
        Address a = new Address();
       // System.out.println(a.toString());

    }

}
