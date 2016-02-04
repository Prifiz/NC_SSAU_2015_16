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
@XmlRootElement(name = "Request")

public class Request implements Serializable {

    private String command = "";
    private User user;

    public Request(String command) {
        this.command = command;
    }

    public Request() {
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
}
