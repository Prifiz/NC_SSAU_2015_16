package team5.library.transmissions;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import team5.library.user.User;

/**
 * Use for request transmission from client to server
 *
 * @author Dmitry
 */
//@XmlType(namespace = "http://www.example.org/package")
@XmlRootElement(name = "Message")

public class Message implements Serializable {

    private String command = "";
    private User user;
    private boolean confirmation;

    public Message(String command) {
        this.command = command;
    }
    
    public Message(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public Message() {
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @XmlElement(name = "command")
    public String getCommand() {
        return command;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
     public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public boolean getConfirmation() {
        return confirmation;
    }
}
