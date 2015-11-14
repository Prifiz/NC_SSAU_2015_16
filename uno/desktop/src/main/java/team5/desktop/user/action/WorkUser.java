/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.user.action;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import team5.desktop.user.Address;
import team5.desktop.user.Initials;
import team5.desktop.user.ServiceInfo;
import team5.desktop.user.User;

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
    
    public User addUser(String name, String surname, String country, String sity, String login, String password, String email, LocalDate bDay){
        if (this.search(login)!=null) {
            return null;
        }
        Initials initials =new Initials(name,surname);
        Address address =new Address(country, sity);
        ServiceInfo serviceInfo =new ServiceInfo( login, password, email);
	User us = new User(initials, address, bDay, serviceInfo);
        arrUsers.add(us);
        return  us;
    }

    //не работает!
    public User editUser(String name, String surname, String country, String sity, String login, String password, LocalDate calendar, String email, LocalDate bDay){ 
	User tmp=this.search(login);
	if(tmp!=null){
            Initials initials =new Initials(name,surname);
            Address address =new Address(country, sity);
            ServiceInfo serviceInfo =new ServiceInfo( login, password, email);
		tmp.setInitials(initials);
		tmp.setAddress(address);
                tmp.setbDay(bDay);
                tmp.setServiceInfo(serviceInfo);
                return tmp;
	}
	else{
		return null;
        }
    }
    
    
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
	while((i<arrUsers.size())&&(!arrUsers.get(i).getServiceInfo().getLogin().equals(login)))
		i++;
        if(i<arrUsers.size())
            return arrUsers.get(i);            
        return null;
    
    }
    
    public String viewUsers(){
        StringBuilder buffer=new StringBuilder();
        for(User u : arrUsers)
            buffer.append(u.toString());
        return buffer.toString();
    }
}

