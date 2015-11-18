package team5.desktop.user.action;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import team5.desktop.user.PrivateInformation;
import team5.desktop.user.ServiceInfo;
import team5.desktop.user.User;
import team5.desktop.user.adress.Address;

public class WorkUser{
    
    private static  WorkUser work = new WorkUser();
    public static WorkUser getWork()
    {
        return work;
    }
    
    private ArrayList<User>  arrUsers;

    public WorkUser() {
        arrUsers = new ArrayList ();
    }
    
    public ArrayList<User> getArrOfUsers()
    {
        return arrUsers;
    }
    public User addUser(String name, String surname, String country, String sity, String login, String password, String email, String  bDay){
        if (this.search(login)!=null) {
            return null;
        }
        
        PrivateInformation  privateInformation =new PrivateInformation(name,surname,stringToLocalDate(bDay));
        Address address =new Address(country, sity);
        ServiceInfo serviceInfo =new ServiceInfo(login, password, email);
	User us = new User(privateInformation, address, serviceInfo);
        arrUsers.add(us);
        return  us;
    }
    public  LocalDate stringToLocalDate(String  s)
    {
       String [] str = s.split("\\.");
       int n = str.length;
       int year = Integer.parseInt(str[2]);
       int month = Integer.parseInt(str[1]);
       int day = Integer.parseInt(str[0]);
       return  LocalDate.of(year,month,day);
    }
    
    
    //не работает!
    /*public User editUser(String name, String surname, String country, String sity, String login, String password, LocalDate calendar, String email, String bDay){ 
        User tmp=this.search(login);
	if(tmp!=null){     
            Address address =new Address(country, sity);
            ServiceInfo serviceInfo =new ServiceInfo( login, password, email);
            PrivateInformation information = new PrivateInformation(name,surname,bDay);
            tmp.setPrivateInformation(information);
            tmp.setAddress(address);
            tmp.setServiceInfo(serviceInfo);
            return tmp;
	}
	else{
            return null;
        }
    }*/

    
    
    public User deleteUser(String login){ 
	User tmp=this.search(login);
	if(tmp!=null)
            arrUsers.remove(tmp);   
	else
            return null;
        return null;
    }
    
   
    
    public User search(String login){
        int i=0;
        if (arrUsers.isEmpty()) {
            return null;
        }
        if (login==null) {
            return  null;
        }
	while((i<arrUsers.size())&&(!arrUsers.get(i).getServiceInfo().getLogin().equals(login))) {
		i++;
        }
        if(i<arrUsers.size()) {
            
            return arrUsers.get(i);     
        }
        return null;
    
    }
    
    public String viewUsers(){
        StringBuilder builder=new StringBuilder();
        for(User u : arrUsers)
            builder.append(u.toString());
        return builder.toString();
    }
}
