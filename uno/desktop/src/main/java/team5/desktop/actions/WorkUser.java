package team5.desktop.actions;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.log4j.Logger;
import team5.desktop.exceptions.UserNotFoundException;
import team5.desktop.user.adress.Address;
import team5.desktop.user.PrivateInformation;
import team5.desktop.user.ServiceInfo;
import team5.desktop.user.User;
import team5.desktop.exceptions.*;
import team5.desktop.user.admin.Admin;

//@XmlRootElement

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {/*"arrUsers"*/},name = "workUser")
@XmlRootElement()
//@XmlType(propOrder = {"user"})
public class WorkUser implements Serializable {


    private static final WorkUser work = new WorkUser();

    private static  Logger log = Logger.getLogger(WorkUser.class);
    //private static WorkUser work = new WorkUser();


    public static WorkUser getWork() {
        return work;
    }
    
    private ArrayList<User> arrUsers;

    
    
    public WorkUser() {
        arrUsers = new ArrayList();
        for (int i = 0; i < arrUsers.size(); i++) {
            try {
                if (arrUsers.get(i).equals(search(Constants.LOGIN_ADMIN))){
                    User admin = new Admin();
                    arrUsers.add(admin);
                }   } catch (UserNotFoundException ex) {
                Logger.getLogger(WorkUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<User> getArrOfUsers() {
        return arrUsers;
    }

    //@XmlElement
    /**
     * 
     * @param name
     * @param surname
     * @param country
     * @param sity
     * @param login
     * @param password
     * @param email
     * @param bDay
     * @return
     * @throws UserExistException 
     */
    public User addUser(String name, String surname, String country, String sity, String login, String password, String email, String bDay)
            throws UserExistException {
//        if (this.search(login)!=null) {
//            return null;
//        }
        try {
            this.search(login);
            throw new UserExistException("User already exist");
        } catch (UserNotFoundException e) {
            PrivateInformation privateInformation = new PrivateInformation(name, surname, stringToLocalDate(bDay));
            Address address = new Address(country, sity);
            ServiceInfo serviceInfo = new ServiceInfo(login, password, email);
            User us = new User(privateInformation, address, serviceInfo);
            arrUsers.add(us);
            return us;
        }
    }

    public User getUserOfIndex(int index) {
        return arrUsers.get(index);
    }

    public int getOfCountUser() {
        return arrUsers.size();
    }

    //@XmlElement
    public void addWorkUser(WorkUser wu) {
        for (int i = 0; i < wu.getOfCountUser(); i++) {
            arrUsers.add(wu.getUserOfIndex(i));
        }
    }

    public LocalDate stringToLocalDate(String s) {
        String[] str = s.split("\\.");
        int n = str.length;
        int year = Integer.parseInt(str[2]);
        int month = Integer.parseInt(str[1]);
        int day = Integer.parseInt(str[0]);
        return LocalDate.of(year, month, day);
    }

    //не работает!
    public User editUser(String name, String surname, String country, String sity, String login, String password, LocalDate calendar, String email, LocalDate bDay)
            throws UserNotFoundException {
        try {
            User tmp = this.search(login);
            //if(tmp!=null){     
            Address address = new Address(country, sity);
            ServiceInfo serviceInfo = new ServiceInfo(login, password, email);
            PrivateInformation information = new PrivateInformation(name, surname, bDay);
            tmp.setPrivateInformation(information);
            tmp.setAddress(address);
            tmp.setServiceInfo(serviceInfo);
            return tmp;
            //}
//	else{
//            return null;
//        }
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Edit " + e.getMessage());
        }
    }

    //@XmlElement
    public void deleteUser(String login)
            throws UserNotFoundException {
        try {
            User tmp = this.search(login);
            //if(tmp!=null)
            arrUsers.remove(tmp);
//	else
//            return null;
//        return null;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Delete " + e.getMessage());
        }
    }

    public User search(String login)
            throws UserNotFoundException {
        //try{
        int i = 0;
        if (arrUsers.isEmpty()) {
            throw new UserNotFoundException("Array of users is empty");
        }
        if (login == null) {
            throw new UserNotFoundException("Uncorrect login");
        }
        while ((i < arrUsers.size()) && (!arrUsers.get(i).getServiceInfo().getLogin().equals(login))) {
            i++;
        }
        if (i < arrUsers.size()) {

            return arrUsers.get(i);
        }
        throw new UserNotFoundException("User not found");
        //}

    }

    public String viewUsers() {
        StringBuilder builder = new StringBuilder();
        for (User user : arrUsers) {
            builder.append(user.toString());
        }
        return builder.toString();
    }

}
