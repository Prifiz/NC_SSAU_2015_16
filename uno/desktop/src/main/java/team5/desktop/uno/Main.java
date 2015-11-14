/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.uno;



import team5.desktop.user.action.SignIn;
import team5.desktop.user.action.WorkUser;
import team5.desktop.gui.StartFrame;
import java.time.LocalDate;

/**
 *
 * @author chanta
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//       
//        User user1 = new User(new Initials("Adam", "Smith"), new Address("USA","VS"),new GregorianCalendar(1995, 12, 12) , new ServiceInfo("ggg", "1231114", "smi@gmai.com"));
//        User user2 = new User(new Initials("Mikki", "Black"), new Address("US","nh"),new GregorianCalendar(1994, 12, 12) , new ServiceInfo("gtrf", "123114", "gdsmi@gmai.com"));
//        User user3 = new User(new Initials("Djo", "Nicco"), new Address("Canada","ho"),new GregorianCalendar(1992, 12, 12) , new ServiceInfo("trt", "12341", "sfdami@gmai.com"));
//        User user4 = new User(new Initials("Adam", "Smith"), new Address("USA","VS"),new GregorianCalendar(1994, 12, 12) , new ServiceInfo("ggrtr", "123411", "smi@gmai.com"));
//        User user5 = new User(new Initials("Berk", "Kok"), new Address("USA","Cd"),new GregorianCalendar(1991, 12, 12) , new ServiceInfo("uyy", "124334", "smiki@gmai.com"));
//        java.awt.EventQueue.invokeLater(new Runnable() {
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartFrame().setVisible(true);
            }
        });
       // ArrayList<User> users = null;
       // users.add(user1);
        WorkUser wu =WorkUser.getWork();
       // WorkUser wu =new WorkUser();
        //wu.addUser("Adam", "Smith", "USA","VS",new GregorianCalendar(1995, 12, 12) , "ggg", "1231114", "smi@gmai.com");
        wu.addUser("bob1", "Clark", "Usa", "XX", "sf", "1234", "email@gmail.com", LocalDate.now());
        wu.addUser("bob2", "Clark", "Usa", "XX", "sf", "1234", "email@gmail.com", LocalDate.now());
        wu.addUser("bob3", "Clark", "Usa", "XX", "sfsd", "1234", "email@gmail.com", LocalDate.now());
        wu.addUser("bob4", "Clark", "Usa", "XX", "sfsdf", "1234", "email@gmail.com", LocalDate.now());
        wu.addUser("bob5", "Clark", "Usa", "XX", "sfsdfa", "1234", "email@gmail.com", LocalDate.now());
        //wu.deleteUser("sf");
        System.out.println( wu.viewUsers());
        SignIn in = new SignIn();
        char[] ch = new char[]{'1','2','3','4'};
        //String charToString = Character.toString(ch);
        in.sign("sf",ch);

    }
    
}

