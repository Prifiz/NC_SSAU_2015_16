
package team5.library.transmissions;

import java.io.Serializable;
import team5.library.user.User;

/**
 * Use for command transmission from client to server
 * @author Dmitry
 */
public class Commands implements Serializable {
    
    private String command="";
    private User user;
    
    public Commands(String command){
        this.command=command;
    }
    
    public void setCommand(String command){
        this.command=command;
    }
    
    public String getCommand(){
        return command;
    }
    
    public void setUser(User user){
        this.user=user;
    }
    
    public User getUser(){
        return user;
    }
}
